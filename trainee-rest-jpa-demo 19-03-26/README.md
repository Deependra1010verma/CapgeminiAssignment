# Trainee Management System

This project is a Spring Boot Maven application developed using Spring REST and Spring Data JPA to manage trainee details.

## Question

Version -1:

Develop a Spring Restfull Controller application to manage list of trainees by an admin. Refer the class diagram below to develop required classes.

Class Diagram 5: Trainee related Classes

Trainee

- traineeId: int
- traineeName: string
- traineeDomain: string
- traineeLocation: string

Note: Create DTO to exchange data

Write end point function in the controller as follows.

1. List All Trainee
2. List Trainee by Name
3. List Trainee By Id
4. Delete Trainee Ny Id
5. Update Trainee Information in the table based on different criteria
6. Insert Trainee

Refer below screen shot for reference

Note Do not Create UI here.

This UI will be created in Module 3

Test your Spring Resfull web service using Postman

## Project Structure

- `controller` for REST endpoints
- `service` for business logic
- `repository` for Spring Data JPA access
- `entity` for database mapping
- `dto` for request and response data transfer
- `exception` for application-specific exception handling

## Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Maven

## API Endpoints

- `GET /api/trainees`
- `GET /api/trainees/name/{traineeName}`
- `GET /api/trainees/{traineeId}`
- `POST /api/trainees`
- `PUT /api/trainees/{traineeId}`
- `DELETE /api/trainees/{traineeId}`

## Running the Project

Use the following command:

```bash
./mvnw spring-boot:run
```

The application runs on:

`http://localhost:8086`

## Testing with Postman

Sample requests are available in `POSTMAN_API_GUIDE.md`.
