
# syntax=docker/dockerfile:experimental
FROM openjdk:11 AS build
WORKDIR /workspace/app

COPY /build/libs/kotlin-restful-api-0.0.1-SNAPSHOT.jar /app/run.jar
CMD ["java", "-jar", "/app/run.jar"]