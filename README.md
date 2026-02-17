# 🏢 Department Service

Department Service is a Spring Boot microservice in the Employee Management System (EMS) ecosystem.  
It manages department-related operations and provides validation endpoints for other services like Employee Service.

---

## 📌 Overview

The Department Service is responsible for:

- Managing departments (CRUD)
- Enforcing unique department codes
- Validating department existence for inter-service communication
- Providing a `/check/{id}` endpoint used by EMS before employee creation
- Applying global exception handling
- Logging with traceId (Micrometer + MDC)

This service follows clean architecture and microservices best practices.

---

## 🏗 Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- ModelMapper
- Spring Validation
- Spring Boot Actuator
- Micrometer (Tracing)
- WebClient (for future integrations)
- Global Exception Handling
- RESTful API Design

---

## 🗂 Project Structure

```
com.example.department
│
├── controller
├── service
│ └── impl
├── repository
├── entity
├── dto
├── config
├── exception
└── util
└── constants
└── enums

```


---

## 🗄 Database Schema

### 📌 `departments` Table


```sql
CREATE TABLE departments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(100) NOT NULL UNIQUE,
    department_head_id BIGINT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

## API Endpoints

✅ Create Department

```
POST /api/v1/departments
```

✅ Get All Departments

```
GET /api/v1/departments
```

✅ Get Department by ID

```
GET /api/v1/departments/{id}
```

✅ Update Department

```
PUT /api/v1/departments/{id}
```

✅ Delete Department

```
DELETE /api/v1/departments/{id}
```

## 🔎 Validation Endpoint (Used by EMS)

```
GET /api/v1/departments/check/{id}
```

Response:

```
{
  "valid": true
}
```

This endpoint is called by Employee Service before assigning a department to an employee.


## 🔄 Inter-Service Communication

Department Service exposes a validation endpoint:

```
/check/{departmentId}
```

Employee Service calls this endpoint using WebClient to ensure:

- Department exists

- Valid reference before saving employee


## 🛡 Validation & Error Handling


- Unique constraint validation for department code

- Global Exception Handling

- Custom Error Response Structure:

```
{
  "errorCode": "VALIDATION_FAILED",
  "errorMessage": "Department code already exists",
  "path": "/api/v1/departments",
  "traceId": "abc123",
  "timestamp": "2025-12-10T12:45:00",
  "httpMethod": "POST"
}
```


# 📊 Logging & Monitoring

- Structured logging

- MDC with traceId


** Spring Boot Actuator endpoints:**

```
/actuator/health

/actuator/metrics

```


## 🚀 How to Run

1️⃣ Configure Database

```
spring.datasource.url=jdbc:mysql://localhost:3306/department_service
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

2️⃣ Run Application

```
mvn spring-boot:run
```

Service runs on:

```
http://localhost:8081
```

## 🧠 Design Decisions

- Clean separation of Controller, Service, Repository

- DTO ↔ Entity mapping using ModelMapper

- Validation handled at both DB and application layer

- Designed to be consumed by EMS microservice

- No business logic leakage outside its domain