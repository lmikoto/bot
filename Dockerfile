FROM maven:3.5.0-jdk-8-alpine

MAINTAINER lmikoto

ADD . /tmp/app/

RUN cd /tmp/app && mvn clean package -X &&\
    mv /tmp/app/target/*.jar /var/lib/app.jar &&\
    rm -rf /tmp/app

ENV LANG="zh_CN.UTF-8"

#ENV spring.profiles.active="prod"

VOLUME ["/logs"]

EXPOSE 8080

ENTRYPOINT java -jar /var/lib/app.jar
