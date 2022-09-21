FROM maven:3.6.3-adoptopenjdk-11 as build

ADD . ./demo-client
WORKDIR demo-client
RUN ./gradlew clean build

FROM openjdk:11-jre-slim

COPY --from=build /demo-client/build/libs/* /usr/app/demo-client.jar
