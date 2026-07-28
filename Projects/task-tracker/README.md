# Task Tracker API

A simple REST API for managing tasks, built with **Java 17+** and **Spring Boot**. This project follows a clean, layered architecture (Controller → Service → Repository) and uses **Spring Data JPA** with an **H2 in-memory database** for persistence.

Built as a hands-on learning project to understand REST API fundamentals, Spring Boot annotations, dependency injection, and layered application design.

## Features

- Full CRUD support for tasks (Create, Read, Update, Delete)
- RESTful endpoints following standard HTTP conventions
- Layered architecture separating HTTP handling, business logic, and data access
- Persistent storage via Spring Data JPA + H2 database
- Built-in H2 web console for inspecting the database directly

## Tech Stack

- **Java 17+**
- **Spring Boot** (Spring Web, Spring Data JPA)
- **H2 Database** (in-memory)
- **Maven** (build tool)

## Project Structure

```
src/main/java/com/tasktracker/task_tracker/
├── TaskTrackerApplication.java   # Application entry point
├── Task.java                     # Entity — maps to the database table
├── TaskController.java           # Handles HTTP requests
├── TaskService.java              # Business logic
└── TaskRepository.java           # Data access (Spring Data JPA)
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven (or use the included `mvnw` / `mvnw.cmd` wrapper — no separate install needed)

### Running the app

Clone the repository, then from the project root:

**macOS/Linux:**
```bash
./mvnw spring-boot:run
```

**Windows:**
```bash
.\mvnw.cmd spring-boot:run
```

The app starts on `http://localhost:8080`.

### H2 Database Console

While the app is running, view the database directly at:

```
http://localhost:8080/h2-console
```

Use these connection settings:
- **JDBC URL:** `jdbc:h2:mem:taskdb`
- **User Name:** `sa`
- **Password:** *(leave blank)*

## API Endpoints

| Method | Endpoint      | Description                |
|--------|---------------|-----------------------------|
| GET    | `/tasks`      | Get all tasks               |
| GET    | `/tasks/{id}` | Get a single task by ID     |
| POST   | `/tasks`      | Create a new task           |
| PUT    | `/tasks/{id}` | Update an existing task     |
| DELETE | `/tasks/{id}` | Delete a task                |

### Example: Create a task

**Request:** `POST /tasks`
```json
{
    "title": "Buy groceries",
    "description": "Milk, eggs, bread",
    "done": false
}
```

**Response:**
```json
{
    "id": 1,
    "title": "Buy groceries",
    "description": "Milk, eggs, bread",
    "done": false
}
```

## Notes

- Data is stored in an in-memory H2 database, so it resets each time the application restarts. Switching to a file-based or external database is a straightforward configuration change in `application.properties`.
- This project is a work in progress — validation, proper error responses (e.g. 404 for missing resources), and API documentation via Swagger are planned next steps.

## License

This project is for educational purposes.
