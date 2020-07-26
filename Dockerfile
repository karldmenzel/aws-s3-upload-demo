#### Build app ####
FROM gradle:6.5-jdk8 AS build-stage

#Copy docker-compose environment variable to this container
ARG PERSONAL_WEBSITE_ENV
ENV PERSONAL_WEBSITE_ENV=${PERSONAL_WEBSITE_ENV}

#Copy the source code
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle build

#### Run app ####
FROM openjdk:15-jdk-alpine

#Copy docker-compose environment variable to this container
ARG PERSONAL_WEBSITE_ENV
ENV PERSONAL_WEBSITE_ENV=${PERSONAL_WEBSITE_ENV}

#Copy the jar from the build stage
ARG JAR_FILE=/home/gradle/src/build/libs/personal-website-backend-0.0.1.jar
COPY --from=build-stage ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]