# Employee Task Manager (Spring Boot + Vanilla Frontend)

A simple full-stack learning project built with Spring Boot as the backend and HTML/CSS/JavaScript as the frontend.  
This project demonstrates clean CRUD operations, DTO usage, service-layer patterns, and a minimal standalone frontend that consumes REST APIs.

---

## Features

### Backend (Spring Boot)
- Create an Employee
- Fetch all Employees
- Layered structure: Controller, Service, Repository, DTO, Model
- Manual DTO conversion (no mapper frameworks)
- MySQL database integration
- CORS enabled for frontend–backend communication

### Frontend
- Plain HTML, CSS, and JavaScript (no frameworks)
- Form to create employees
- Table to display employee list
- Uses Fetch API to call backend endpoints

---

## Technologies Used

### Backend
- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Maven

### Frontend
- HTML
- CSS
- JavaScript (Fetch API)
- VS Code Live Server or Python HTTP server

---

## Project Structure



---

## Running the Backend

### 1. Create MySQL Database
```sql
CREATE DATABASE task_manager;

Configure MySQL Credentials:
spring.datasource.url=jdbc:mysql://localhost:3306/task_manager
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update









