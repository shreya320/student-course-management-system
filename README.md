# Learning Backend - Spring Boot Project

A backend application built using Spring Boot to learn and implement core backend development concepts. The project models a simple Student-Course-Enrollment system and demonstrates modern backend architecture, database management, API design, validation, security, and authentication practices.

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Spring Security
- JWT Authentication
- Maven

---

## Features Implemented

### REST API Development

- RESTful API design
- GET, POST endpoints
- Request parameters
- Path variables
- ResponseEntity handling

### Layered Architecture

The project follows a standard layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

- Controller Layer
- Service Layer
- Repository Layer

---

## Database & Persistence

### JPA / Hibernate

Implemented:

- Entity creation
- Database mapping
- Primary key generation
- Repository pattern

### Entity Relationships

Implemented relationships using:

- @OneToMany
- @ManyToOne
- @JoinColumn

Entities:

- Student
- Course
- Enrollment

---

## PostgreSQL Integration

Configured Spring Boot to work with PostgreSQL instead of H2.

Topics learned:

- PostgreSQL configuration
- Datasource setup
- Hibernate DDL management
- Database persistence

---

## Custom Queries

Implemented custom JPQL queries using:

```java
@Query
```

### Query Types

- Dynamic filtering
- Multiple conditions
- Relationship-based queries

Examples:

- Marks greater than given value
- Marks and grade filtering
- Student name search

---

## Aggregation Queries

Implemented:

- COUNT()
- AVG()
- MAX()
- MIN()

Used for reporting and analytics style APIs.

---

## DTOs (Data Transfer Objects)

Implemented:

### Request DTOs

Used to receive client input.

Example:

- StudentRequestDTO

### Response DTOs

Used to return controlled API responses.

Example:

- StudentResponseDTO

### Summary DTOs

Used to return only necessary fields.

Example:

- StudentSummaryDTO

Topics learned:

- Entity vs DTO separation
- Request/Response mapping
- API contract design

---

## Validation

Implemented validation using:

- @NotNull
- @NotBlank
- @Email
- @Size

Validation is performed before data reaches the service layer.

---

## Global Exception Handling

Implemented:

```java
@ControllerAdvice
```

Features:

- Validation error handling
- Centralized exception management
- Custom error responses

---

## Pagination & Sorting

Implemented:

### Pagination

- Pageable
- Page<T>

### Sorting

- Ascending sorting
- Descending sorting

### Combined Usage

- Page + Sort

Example:

```text
?page=0&size=5&sort=name,asc
```

---

## Fetch Strategies

Learned:

### Lazy Loading

Data fetched only when required.

### Eager Loading

Related data fetched immediately.

Topics covered:

- Performance considerations
- Relationship fetching strategies

---

## Transaction Management

Implemented:

```java
@Transactional
```

Topics learned:

- Atomic operations
- Rollback behavior
- Database consistency

---

## Spring Security

Implemented:

- Spring Security configuration
- Public and protected endpoints
- Authentication flow
- Authorization concepts

---

## Password Security

Implemented:

### BCrypt Password Hashing

Topics learned:

- Password hashing
- Password verification
- Secure password storage

---

## JWT Authentication

Implemented:

- JWT generation
- JWT validation
- Bearer token authentication
- Protected APIs

Authentication Flow:

```text
Register
    ↓
Hash Password
    ↓
Login
    ↓
Generate JWT
    ↓
Access Protected APIs
```

---

## Key Concepts Learned

- Backend architecture
- API design
- Database design
- ORM with Hibernate
- DTO pattern
- Validation
- Exception handling
- Pagination
- Sorting
- Security
- Authentication
- Authorization
- JWT
- Transaction management

---

## Learning Outcome

This project was built to gain hands-on experience with modern backend development using Spring Boot. It covers the core concepts required for building secure, scalable, and maintainable backend applications and serves as a foundation for more advanced backend development topics.