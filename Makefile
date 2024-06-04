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
