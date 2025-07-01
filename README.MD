# FinCloud - Financial Services Microservices Platform

[![GitHub](https://img.shields.io/github/license/AshGaur/fincloud-microservices)](https://github.com/AshGaur/fincloud-microservices/blob/master/LICENSE)
[![Docker](https://img.shields.io/docker/cloud/build/ashgaur/fincloud)](https://hub.docker.com/r/ashgaur/fincloud)

FinCloud is a modern, distributed financial services platform built using Spring Boot microservices architecture. This project showcases my expertise in implementing various microservice design patterns and building scalable, resilient cloud-native applications.

## Project Overview

FinCloud demonstrates the implementation of multiple microservice design patterns using Spring Boot and Spring Cloud, following industry-standard practices and modern development approaches.

## Technical Stack

- **Core Framework**: Spring Boot 3.5.0, Spring Cloud 2025.0.0
- **Language**: Java 17
- **Build Tool**: Maven
- **Containerization**: Docker, Docker Compose

## Implemented Microservice Design Patterns

### Service Discovery Pattern
- **Implementation**: Spring Cloud Netflix Eureka
- **Features**:
  - Service registration and discovery
  - Dynamic service lookup
  - Health checks and service status monitoring

### API Gateway Pattern
- **Implementation**: Spring Cloud Gateway
- **Features**:
  - Dynamic routing between microservices
  - Request filtering and transformation
  - Rate limiting and request validation
  - API versioning and documentation

### Circuit Breaker Pattern
- **Implementation**: Spring Cloud Circuit Breaker with Resilience4j
- **Features**:
  - Circuit breaker for graceful degradation
  - Bulkhead pattern for resource isolation
  - Retry mechanisms with exponential backoff
  - Rate limiting to prevent service overload
  - Fallback strategies for service failures

### Configuration Management Pattern
- **Implementation**: Spring Cloud Config
- **Features**:
  - Centralized configuration management
  - Git-based configuration repository
  - Environment-specific configurations
  - Dynamic configuration updates

### Database per Service Pattern
- **Implementation**: JPA with individual databases
- **Features**:
  - Independent database per microservice
  - Data isolation between services
  - Service-specific data models
  - JPA Auditing for data tracking

### Service-to-Service Communication Pattern
- **Implementation**: Spring Cloud OpenFeign
- **Features**:
  - Declarative REST client implementation
  - Service discovery integration
  - Request/response logging
  - Circuit breaker integration

### Observability Pattern
- **Implementation**: Comprehensive observability stack
- **Components**:
  - **Logging**: Grafana Loki
  - **Metrics**: Micrometer + Prometheus
  - **Tracing**: OpenTelemetry + Tempo
  - **Visualization**: Grafana
  - **Health Checks**: Spring Boot Actuator

## Environment Setup

The project supports three main environments:

1. **Development (`default`)**
   - Default development environment
   - Ideal for local development and testing
   - Includes basic monitoring setup

2. **Quality Assurance (`qa`)**
   - Pre-production environment for testing
   - More comprehensive monitoring
   - Performance testing setup

3. **Production (`prod`)**
   - Production-ready configuration
   - Full observability stack
   - Optimized for performance

The `observability` directory contains configuration files for monitoring tools (Grafana, Prometheus, Loki, Tempo) used across all environments.

## Getting Started

1. **Prerequisites**
   - Java 17 or higher
   - Maven 3.8.x
   - Docker and Docker Compose
   - Git

2. **Clone the Repository**
```bash
git clone https://github.com/AshGaur/fincloud-microservices.git
cd fincloud-microservices
```

3. **Build the Project**
```bash
# Build all microservices
mvn clean install
```

4. **Run with Docker Compose**
```bash
cd docker-compose
docker-compose up
```

## Project Statistics

- Total Lines of Code: 10K+
- Number of Microservices: 5
- Docker Containers: 15+
- Test Coverage: 85%

---

This project showcases my ability to:
- Implement various microservice design patterns
- Design and implement scalable microservices architectures
- Build resilient and fault-tolerant systems
- Work with containerization and cloud-native technologies
- Follow best practices in software development
- Create maintainable and well-documented codebases