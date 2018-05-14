FROM maven:3.5.3-jdk-8-slim as builder
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
# Install amven dependencies
COPY pom.xml /usr/src/app/
COPY core/pom.xml /usr/src/app/core/
COPY web/pom.xml /usr/src/app/web/
RUN mvn -T 1C install

COPY . /usr/src/app
RUN mvn package -Dmaven.skip.test=true

FROM payara/server-full:5.181
COPY --from=builder /usr/src/app/web/target/runetracker.war $DEPLOY_DIR
