FROM maven:3.5.0-jdk-8-alpine

MAINTAINER lmikoto

ENV BUILD_HOME=/tmp/app

WORKDIR $BUILD_HOME

COPY pom.xml $BUILD_HOME

RUN mvn dependency:go-offline -B

COPY ./src $BUILD_HOME/src

RUN mvn package -X &&\
    mv $BUILD_HOME/target/*.jar /var/lib/app.jar &&\
    rm -rf /tmp/app

ENV LANG="zh_CN.UTF-8"

#ENV spring.profiles.active="prod"

VOLUME ["/logs"]

EXPOSE 8080

ENTRYPOINT java -jar /var/lib/app.jar
