
# syntax=docker/dockerfile:experimental
FROM openjdk:11 AS build
WORKDIR /workspace/app

COPY . /workspace/app
RUN --mount=type=cache,target=/root/.gradle ./gradlew clean build

FROM openjdk:11
COPY --from=BUILD /workspace/app/build/libs/kotlin-restful-api-0.0.1-SNAPSHOT.jar /app/run.jar
CMD ["java", "-jar", "/app/run.jar"]