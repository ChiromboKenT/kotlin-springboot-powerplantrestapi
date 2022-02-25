# API Spec (Power Plant Api)

## Author Kenny Chirombo

# Live Hosted on Azure

- http://powerplantapi.southafricanorth.azurecontainer.io:8080/
- Run in Post man to include X-Api-Key : a4db08b7-5729-4ba9-8c08-f2df493465a

## Running Locally

clone repository

```
  git clone https://github.com/ChiromboKenT/kotlin-springboot-powerplantrestapi.git
```

- Run Docker Containers

  using docker-compose

  ```yml
  # docker-compose.yml
  version: "3.9"
  services:
    api-powerplant:
      build:
        context: .
        dockerfile: Dockerfile
      depends_on:
        - dbpostgresql
      container_name: powerplant-api
      ports:
        - "8080:8080"
      environment:
        DATABASE_USERNAME: powerplant_test
        DATABASE_PASSWORD: PNSJkxXvVNDAhePMuExTBuRR
        DATABASE_URL: jdbc:postgresql://dbpostgresql:5432/powerplant
    dbpostgresql:
      container_name: "postgres-powerplant"
      image: postgres:12
      ports:
        - "5432:5432"
      environment:
        POSTGRES_PASSWORD: PNSJkxXvVNDAhePMuExTBuRR
        POSTGRES_USER: powerplant_test
        POSTGRES_DB: powerplant
  ```

## Docker Image

```dockerfile

# Dockerfile
FROM openjdk:11 AS build
WORKDIR /workspace/app

COPY . /workspace/app
RUN --mount=type=cache,target=/root/.gradle ./gradlew clean build

FROM openjdk:11
COPY --from=BUILD /workspace/app/build/libs/kotlin-restful-api-0.0.1-SNAPSHOT.jar /app/run.jar
CMD ["java", "-jar", "/app/run.jar"]

```

## Create Container And Running App

```bashpro shell script

docker-compose -f docker-compose.yml up -d

```

## Check Logs

```bashpro shell script
# check running container
docker container ls
# check log apps
docker container logs powerplant-api

```

## API KEY

Api Key Configured for Testing

- Request :
  - Header :
    - X-Api-Key : a4db08b7-5729-4ba9-8c08-f2df493465a

## Create New Power Plant

- Request:

  - Method : POST
  - Endpoint : `/api/power-plant`
  - Header :
    - Content-Type: application/json
    - Accept: application/json
    - X-Api-Key: api_keys
  - Body : `json`

  ```json
  {
    "generatorId": "UNIT4",
    "plantName": "Alakanuk",
    "pstatabb": "AK",
    "year": "2019",
    "generatorStatus": "RE",
    "genntan": "12042"
  }
  ```

  - Response :
    ```json
    {
      "code": 201,
      "status": "CREATED",
      "data": {
          "sequenceNumber": 3,
          "generatorId": "UNIT4",
          "plantName": "Alakanuk",
          "pstatabb": "AK",
          "year": "2019",
          "generatorStatus": "RE",
          "genntan": "12042",
          "createdAt": "2022-02-24T01:04:39.147+00:00",
          "updatedAt": null
      }
    ```

## Get Single Power Plant

- Request:

  - Method : GET
  - Endpoint : `/api/power-plant/{sequenceNumber}`
  - Header :
    - Accept: application/json
    - X-Api-Key: api_keys

- Response :
  ```json
  {
    "code": 200,
    "status": "OK",
    "data": {
      "sequenceNumber": 1,
      "generatorId": "UNIT4",
      "plantName": "Alakanuk",
      "pstatabb": "AK",
      "year": "2019",
      "generatorStatus": "RE",
      "genntan": "12042",
      "createdAt": "2022-02-23T21:34:17.731+00:00",
      "updatedAt": null
    }
  }
  ```

## Update Power Plant

- Request:
  - Method : PUT
  - Endpoint : `/api/power-plant/{sequenceNumber}`
  - Header :
    - Content-Type: application/json
    - Accept: application/json
    - X-Api-Key: api_keys
  - Body : `json`
  ```json
  {
    "sequenceNumber": 2,
    "generatorId": "23E",
    "plantName": "Alukele",
    "pstatabb": "#rER",
    "year": "2007",
    "generatorStatus": "RE",
    "genntan": "123456",
    "createdAt": "2022-02-23T23:54:34.499+00:00",
    "updatedAt": null
  }
  ```
- Response :
  ```json
  {
    "code": 200,
    "status": "OK",
    "data": {
      "sequenceNumber": 2,
      "generatorId": "23E",
      "plantName": "Alukele",
      "pstatabb": "#rER",
      "year": "2007",
      "generatorStatus": "RE",
      "genntan": "123456",
      "createdAt": "2022-02-23T23:54:34.499+00:00",
      "updatedAt": null
    }
  }
  ```

## List Power Plants

- Request:

  - Method : GET
  - Endpoint : `/api/power-plant`
  - Header :
    - Accept: application/json
    - X-Api-Key: api_keys
  - Query Param :
    - location : string,
    - page : number,
    - size : numer,
    - top : number,
    - bottom: number

- Response :
  ```json
  {
    "code": 200,
    "status": "OK",
    "data": [
      {
        "sequenceNumber": 1,
        "generatorId": "UNIT4",
        "plantName": "Alakanuk",
        "pstatabb": "AK",
        "year": "2019",
        "generatorStatus": "RE",
        "genntan": "12042",
        "createdAt": "2022-02-23T21:34:17.731+00:00",
        "updatedAt": null
      },
      {
        "sequenceNumber": 2,
        "generatorId": "23E",
        "plantName": "Alukele",
        "pstatabb": "#rER",
        "year": "2007",
        "generatorStatus": "RE",
        "genntan": "123456",
        "createdAt": "2022-02-23T23:54:34.499+00:00",
        "updatedAt": null
      },
      {
        "sequenceNumber": 3,
        "generatorId": "UNIT4",
        "plantName": "Alakanuk",
        "pstatabb": "AK",
        "year": "2019",
        "generatorStatus": "RE",
        "genntan": "12042",
        "createdAt": "2022-02-24T01:04:39.147+00:00",
        "updatedAt": null
      }
    ]
  }
  ```

## Delete Power Plant

- Request:

  - Method : DELETE
  - Endpoint : `/api/power-plant/{sequenceNumber}`
  - Header :
    - Accept: application/json
    - X-Api-Key: api_keys

- Response :
  ```json
  {
    "code": "number",
    "status": "string",
    "data": "string"
  }
  ```
