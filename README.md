# Clustered Data Warehouse

## Description

This project is a Spring Boot application for handling warehouse deals. It includes the functionality to receive and store foreign exchange (FX) deal details in a PostgreSQL database, ensuring no duplicate deals are imported and each valid row is saved in the database.

## Features

- Accept and validate deal details
- Prevent duplicate deal imports
- Save deals to a PostgreSQL database
- Proper error and exception handling
- Logging for important events and errors
- Unit tests for service and controller layers

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate Validator
- PostgreSQL
- Docker
- Docker Compose
- Maven

## Requirements

- Docker
- Docker Compose
- Java 17
- Maven

## Setup

### 1. Clone the Repository

```sh
git clone https://github.com/yourusername/ClusteredData_Warehouse.git
cd ClusteredData_Warehouse
```


### 2. Build the Application

Ensure you have Maven installed and run the following command to build the application:

```sh
./mvnw clean package
```

### 3. Docker Compose

Make sure Docker and Docker Compose are installed on your system. To build and start the Docker containers, run:

```sh
docker-compose up --build
```

This command will:

- Build the Docker image for the Spring Boot application using the `Dockerfile`.
- Start the PostgreSQL database container.
- Start the Spring Boot application container.

### 4. Access the Application

Once Docker Compose has started the services, you can verify that your application is running by accessing it through the browser at:

http://localhost:8080

## Project Structure

/your-project-root
│
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── clusterData
│   │               └── warehouse
│   │                   ├── controller
│   │                   │   └── DealController.java
│   │                   ├── dto
│   │                   │   └── DealDTO.java
│   │                   ├── entity
│   │                   │   └── Deal.java
│   │                   ├── exception
│   │                   │   └── DuplicateDealException.java
│   │                   ├── mapper
│   │                   │   └── DealMapper.java
│   │                   ├── repository
│   │                   │   └── DealRepository.java
│   │                   └── service
│   │                       └── DealService.java
│   └── test
│       └── java
│           └── com
│               └── clusterData
│                   └── warehouse
│                       ├── controller
│                       │   └── DealControllerTest.java
│                       └── service
│                           └── DealServiceTest.java
│
├── target
│   └── warehouse-0.0.1-SNAPSHOT.jar
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md


## Unit Testing

Unit tests are provided for the service and controller layers using JUnit and Mockito. To run the tests, use the following Maven command:

\```sh
./mvnw test
\```

## Logging

Logging is configured using SLF4J with Logback. Logs are output to both the console and a log file located at `logs/app.log`.

## Contact

For any questions or issues, please contact:

- Name: Abdallah Safi
- Email: mr.abdallahsafi@example.com
- GitHub: [AbdallahSafi](https://github.com/AbdallahSafi)