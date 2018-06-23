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

# First make a jar without any sources to install the dependencies (For better caching on docker)
COPY settings.gradle build.gradle $APP_HOME
RUN gradle bootJar --no-daemon

# Create the applications
COPY . $APP_HOME
RUN gradle bootJar --no-daemon

FROM openjdk:8
WORKDIR /usr/app/
COPY --from=builder /home/gradle/build/libs/runetracker-1.0.0-SNAPSHOT.jar /usr/app/
CMD ["java", "-jar", "/usr/app/runetracker-1.0.0-SNAPSHOT.jar"]
