FROM ubuntu:18.04 as builder

# Install gradle
ENV GRADLE_VERSION=4.8.1
ENV GRADLE_HOME /home/user/gradle-$GRADLE_VERSION
ENV PATH $GRADLE_HOME/bin:$PATH

RUN apt-get update \
    && apt-get install -y wget unzip openjdk-8-jdk
RUN wget -P /home/user/ --quiet https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip && \
    cd /home/user/ && unzip gradle-$GRADLE_VERSION-bin.zip && rm gradle-$GRADLE_VERSION-bin.zip

# Download the dependencies
ENV APP_HOME=/home/gradle/
RUN mkdir -p $APP_HOME
WORKDIR $APP_HOME

COPY settings.gradle build.gradle $APP_HOME
COPY core/build.gradle $APP_HOME/core/
COPY web/build.gradle $APP_HOME/web/
RUN gradle web:war --no-daemon

# Package the application
COPY . $APP_HOME
RUN gradle web:war --no-daemon

FROM payara/server-full:5.181
COPY --from=builder /home/gradle/web/build/libs/web-1.0.0-SNAPSHOT.war $DEPLOY_DIR/runetracker.war
