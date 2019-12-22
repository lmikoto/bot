FROM openjdk:8

MAINTAINER lmikoto

WORKDIR /var/lib/

COPY ./target/*.jar app.jar

#ENV spring.profiles.active=prod

EXPOSE 8080

ENTRYPOINT java -jar app.jar --spring.profiles.active=prod
