
# MaintainX — Equipment Maintenance Management System

A Java-based backend application built using Spring Boot, Spring Data JPA, Hibernate, and SQL databases to manage equipment assets, preventive maintenance schedules, and work-order lifecycles through RESTful APIs.

## Overview

MaintainX is an equipment maintenance management system designed to simplify equipment tracking, preventive maintenance planning, and maintenance work-order management.

The application follows a layered architecture that separates controllers, services, repositories, and entity models. It demonstrates object-oriented programming, CRUD operations, request validation, exception handling, database persistence, and automated testing.

## Features

### Equipment Management
- Create, retrieve, update, and delete equipment records.
- Store equipment name, category, location, serial number, purchase date, and status.
- Search equipment by name or category.
- Filter equipment by operational status.
- Enforce unique equipment serial numbers.

### Preventive Maintenance
- Create and manage preventive maintenance schedules.
- Associate maintenance schedules with equipment.
- Configure maintenance frequency in days.
- Track upcoming maintenance due dates.
- Store maintenance notes and inspection details.

### Work Order Management
- Create, retrieve, update, and delete work orders.
- Assign work orders to maintenance personnel or teams.
- Set priority levels and due dates.
- Track work-order status throughout its lifecycle.
- Update work-order status independently.
- Filter work orders by status or priority.

### Backend Engineering
- RESTful API development using Spring Boot.
- Layered architecture using controllers, services, and repositories.
- Database persistence using Spring Data JPA and Hibernate.
- Request validation using Jakarta Bean Validation.
- Centralized exception handling.
- JUnit 5 testing support.
- Maven-based dependency management.

## Technology Stack

| Technology | Purpose |
|---|---|
| Java 17 | Backend programming |
| Spring Boot | Application framework |
| Spring Web | REST API development |
| Spring Data JPA | Database access |
| Hibernate | Object-relational mapping |
| MySQL | Relational database |
| H2 Database | Local development and testing |
| Jakarta Bean Validation | Request validation |
| JUnit 5 | Automated testing |
| Maven | Build and dependency management |
| Git and GitHub | Version control |

## Architecture

```text
Client / Postman
       |
       v
REST Controllers
       |
       v
Service Layer
       |
       v
Spring Data JPA Repositories
       |
       v
Hibernate ORM
       |
       v
H2 / MySQL Database
```

## Project Structure

```text
MaintainX-Equipment-Maintenance-Management-System/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/com/maintainx/
    │   │   ├── MaintainXApplication.java
    │   │   ├── controller/
    │   │   ├── service/
    │   │   ├── repository/
    │   │   ├── model/
    │   │   └── exception/
    │   └── resources/
    │       ├── application.properties
    │       └── application-mysql.properties
    └── test/
        └── java/com/maintainx/
            └── MaintainXApplicationTests.java
```

## Prerequisites

Install the following before running the application:

- Java JDK 17 or later
- Apache Maven 3.8 or later
- Git (optional)
- Postman (recommended for API testing)

Verify your installations:

```bash
java -version
mvn -version
```

## Getting Started

### 1. Clone the Repository

Replace `YOUR_USERNAME` with your GitHub username.

```bash
git clone https://github.com/YOUR_USERNAME/MaintainX-Equipment-Maintenance-Management-System.git
```

### 2. Navigate to the Project

```bash
cd MaintainX-Equipment-Maintenance-Management-System
```

### 3. Build the Application

```bash
mvn clean package
```

### 4. Run the Application

```bash
mvn spring-boot:run
```

The application runs at:

```text
http://localhost:8080
```

API base URL:

```text
http://localhost:8080/api
```

The first build requires an internet connection to download Maven dependencies.

## Database Configuration

### H2 Database

The default configuration uses an in-memory H2 database. No separate database server is required.

H2 Console:

```text
http://localhost:8080/h2-console
```

Connection details:

```text
JDBC URL: jdbc:h2:mem:maintainxdb
Username: sa
Password: (leave blank)
```

The in-memory database is intended for development and testing. Data may not persist after application restarts.

### MySQL Configuration

Create a database:

```sql
CREATE DATABASE maintainx;
```

Configure your environment variables in PowerShell:

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/maintainx"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="your_password"
```

Run the application with the MySQL profile:

```powershell
mvn spring-boot:run "-Dspring-boot.run.profiles=mysql"
```

Never commit database passwords or other secrets to GitHub.

## REST API Endpoints

All endpoints are available under `/api`.

### Equipment APIs

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/equipment` | Retrieve all equipment |
| GET | `/api/equipment/{id}` | Retrieve equipment by ID |
| POST | `/api/equipment` | Create equipment |
| PUT | `/api/equipment/{id}` | Update equipment |
| DELETE | `/api/equipment/{id}` | Delete equipment |

Search by name or category:

```http
GET /api/equipment?search=pump
```

Filter by equipment status:

```http
GET /api/equipment?status=ACTIVE
```

Example request body:

```json
{
  "name": "Hydraulic Pump A1",
  "category": "Pump",
  "location": "Plant 1",
  "serialNumber": "HP-A1-001",
  "status": "ACTIVE",
  "purchaseDate": "2025-01-15"
}
```

Supported equipment statuses:

- `ACTIVE`
- `INACTIVE`
- `UNDER_MAINTENANCE`

### Preventive Maintenance APIs

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/maintenance-schedules` | Retrieve all schedules |
| GET | `/api/maintenance-schedules/{id}` | Retrieve a schedule |
| POST | `/api/maintenance-schedules` | Create a schedule |
| PUT | `/api/maintenance-schedules/{id}` | Update a schedule |
| DELETE | `/api/maintenance-schedules/{id}` | Delete a schedule |

Example request body:

```json
{
  "equipmentId": 1,
  "title": "Monthly Inspection",
  "frequencyDays": 30,
  "nextDueDate": "2026-11-15",
  "notes": "Inspect seals and vibration"
}
```

The referenced equipment must exist before a maintenance schedule can be created or updated.

### Work Order APIs

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/work-orders` | Retrieve work orders |
| GET | `/api/work-orders/{id}` | Retrieve a work order |
| POST | `/api/work-orders` | Create a work order |
| PUT | `/api/work-orders/{id}` | Update a work order |
| PATCH | `/api/work-orders/{id}/status` | Update work-order status |
| DELETE | `/api/work-orders/{id}` | Delete a work order |

Filter by status:

```http
GET /api/work-orders?status=OPEN
```

Filter by priority:

```http
GET /api/work-orders?priority=HIGH
```

Update a work-order status:

```http
PATCH /api/work-orders/1/status?status=IN_PROGRESS
```

Example request body:

```json
{
  "equipmentId": 1,
  "title": "Unusual Vibration",
  "description": "Investigate vibration during operation",
  "priority": "HIGH",
  "status": "OPEN",
  "assignedTo": "Maintenance Team",
  "dueDate": "2026-11-01"
}
```

Supported priorities:

- `LOW`
- `MEDIUM`
- `HIGH`
- `CRITICAL`

Supported work-order statuses:

- `OPEN`
- `IN_PROGRESS`
- `ON_HOLD`
- `COMPLETED`
- `CANCELLED`

Dates must follow the `YYYY-MM-DD` format.

## Validation and Exception Handling

The application uses Jakarta Bean Validation and centralized exception handling.

| HTTP Status | Description |
|---|---|
| 200 OK | Request completed successfully |
| 201 Created | Resource created successfully |
| 204 No Content | Resource deleted successfully |
| 400 Bad Request | Invalid request data |
| 404 Not Found | Requested resource does not exist |
| 409 Conflict | Database constraint conflict |

Validation includes required fields, string-length constraints, valid enum values, and maintenance frequency constraints.

## Testing

Run automated tests using:

```bash
mvn test
```

The included tests cover application-context startup and equipment persistence.

Additional recommended tests include:

- Equipment CRUD operations
- Duplicate serial-number handling
- Invalid request validation
- Missing-resource scenarios
- Work-order status updates
- Maintenance schedule validation

## Development Workflow

1. Implement features using the layered architecture.
2. Add validation and exception handling.
3. Write and run automated tests.
4. Review code changes.
5. Commit changes using Git.
6. Update documentation when APIs change.

## Future Enhancements

Potential improvements include:

- Spring Security authentication and role-based authorization.
- Pagination and sorting.
- Database migrations using Flyway or Liquibase.
- Automated reminders for upcoming maintenance.
- Work-order history and audit logging.
- Dashboard analytics and maintenance reports.
- Docker-based deployment.
- CI/CD workflows.
- OpenAPI/Swagger documentation.

These are planned enhancements and are not represented as existing functionality.

## Learning Outcomes

This project provides practical experience with:

- Java object-oriented programming.
- Spring Boot REST API development.
- Layered backend architecture.
- SQL databases and ORM persistence.
- CRUD operations and data validation.
- Exception handling and HTTP status codes.
- JUnit-based testing.
- Maven and Git workflows.

## License

This project is intended for educational and portfolio use. Add a license file if you wish to define formal permissions for reuse and distribution.

---

**MaintainX — Equipment Maintenance Management System**

Built with Java, Spring Boot, Spring Data JPA, Hibernate, and SQL database technologies.
