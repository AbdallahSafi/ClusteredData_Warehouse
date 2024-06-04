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

### 5.Project Structure

```
/warehouse
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
```


### 6.Using the Makefile
If you want to streamline running the application, you can use a Makefile.

#### Makefile
The Makefile provides commands to build, run, stop, clean, and test the application.

```
# Define the default goal
.DEFAULT_GOAL := help

# Variables
DOCKER_COMPOSE = docker-compose
MVN = ./mvnw

# Check the operating system
ifeq ($(OS),Windows_NT)
    MVN = mvnw.cmd
endif

# Targets
help: ## Display this help message
	@echo "Usage: make [target]"
	@echo
	@echo "Targets:"
	@awk 'BEGIN {FS = ":.*##"; printf "  %-20s %s\n", "Target", "Description"; printf "  %-20s %s\n", "------", "-----------";} /^[a-zA-Z_-]+:.*?##/ { printf "  %-20s %s\n", $$1, $$2 }' $(MAKEFILE_LIST)

build: ## Build the application using Maven
	$(MVN) clean package

run: ## Build and run the Docker containers
	$(DOCKER_COMPOSE) up --build

stop: ## Stop the Docker containers
	$(DOCKER_COMPOSE) down

clean: ## Clean up Docker containers, networks, and volumes
	$(DOCKER_COMPOSE) down -v

test: ## Run unit tests using Maven
	$(MVN) test
```
#### Usage
- Build the Application:

```sh
make build
```

- Run the Application:

```sh
make run
```

- Stop the Application:

```sh
make stop
```

- Clean up Docker resources:

```sh
make clean
```

- Run Unit Tests:

```sh
make test
```


### 7.Unit Testing

Unit tests are provided for the service and controller layers using JUnit and Mockito. To run the tests, use the following Maven command:

```sh
./mvnw test
```

### 8.Logging

Logging is configured using SLF4J with Logback. Logs are output to both the console and a log file located at `logs/app.log`.

### 9.Contact

For any questions or issues, please contact:

- Name: Abdallah Safi
- Email: mr.abdallahsafi@example.com
- GitHub: [AbdallahSafi](https://github.com/AbdallahSafi)