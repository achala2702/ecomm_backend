# E-Commerce Microservices Backend with Spring Boot

## Overview

This repository contains a microservices-based backend for an e-commerce platform built with Spring Boot. It uses a suite of Spring Cloud technologies to enable service discovery, centralized configuration, API gateway routing, secure JWT authentication, and PostgreSQL persistence.

### Services

| Service Name     | Description                               | Port  | Notes                               |
|------------------|-------------------------------------------|-------|-----------------------------------|
| **api-gateway**  | Single entry point; routes requests to microservices | 8080  | No DB required                    |
| **auth-server**  | Authentication and authorization with JWT | 8081  | Requires PostgreSQL credentials   |
| **config-server**| Central configuration management          | 8090  | No DB required                    |
| **discovery-server** (Eureka) | Service registry and discovery            | 8761  | No DB required                    |
| **order-server** | Order processing and management            | 8083  | Requires PostgreSQL credentials   |
| **product-server**| Product catalog management                  | 8082  | Requires PostgreSQL credentials   |

---
### Client API Endpoints through API Gateway

- **User Authentication (auth-server)**
  - `POST http://localhost:8080/auth/register` — Register a new user
  - `POST http://localhost:8080/auth/login` — User login

- **Product Management (product-server)**
  - `GET http://localhost:8080/product/get-products` — Fetch all products
  - `GET http://localhost:8080/product/{id}` — get product by ID
  - `POST http://localhost:8080/product/add-product` — Add a new product

- **Order Management (order-server)**
  - `POST http://localhost:8080/order/create-order` — Create a new order
---

## Technologies Used

- Spring Boot Microservices  
- Spring Cloud Netflix Eureka (Service Discovery)  
- Spring Cloud Config Server (Centralized Config)  
- Spring Cloud Gateway (API Gateway)  
- Spring Security + JWT (Authentication)  
- PostgreSQL (Database)  
- OpenFeign (Inter-service Communication)  

---

## Environment Variables

Set the following environment variables for database-connected services (`auth-server`, `order-server`, `product-server`) in `config-server`'s **resources/config** directory:

| Variable                      | Description                      |
|-------------------------------|--------------------------------|
| `SPRING_DATASOURCE_USERNAME`  | PostgreSQL database username    |
| `SPRING_DATASOURCE_PASSWORD`  | PostgreSQL database password    |

> **Note:** For the services requiring databases, update the database URLs and credentials inside the configuration files located in the `config-server`'s **resources/config** directory.
The `api-gateway`, `config-server`, and `discovery-server` **do not** require database credentials.

---

## Getting Started

### 1. Start the Config Service

```bash
cd config-server
./mvnw spring-boot:run
```

### 2. Start the Eureka Discovery Service

```bash
cd discovery-server
./mvnw spring-boot:run
```

### 3. Start the Auth Service

```bash
export SPRING_DATASOURCE_USERNAME=your_db_username
export SPRING_DATASOURCE_PASSWORD=your_db_password

cd auth-server
./mvnw spring-boot:run
```

### 4. Start the Product Service

```bash
export SPRING_DATASOURCE_USERNAME=your_db_username
export SPRING_DATASOURCE_PASSWORD=your_db_password

cd product-server
./mvnw spring-boot:run
```

### 5. Start the Order Service

```bash
export SPRING_DATASOURCE_USERNAME=your_db_username
export SPRING_DATASOURCE_PASSWORD=your_db_password

cd order-server
./mvnw spring-boot:run
```
### 6. Start the API Gateway

```bash
cd api-gateway
./mvnw spring-boot:run
```