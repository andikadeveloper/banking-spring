# Banking microservice app

## Technologies
- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- Spring Postgres
- Lombok

## Services
- **Account**: Responsible for managing the accounts.
- **Transaction**: Responsible for managing the transactions.
- **User**: Responsible for managing the users.

## Structure
```
|── bootstrap
├── config
├── controller
├── model
│        ├── dto
│        │       ├── request
│        │       └── response
│        └── entity
├── repository
├── service
│        ├── contract
│        └── impl
```
- **bootstrap**: Contains the class that will be executed first when the application starts.
- **config**: Contains the configuration classes.
- **controller**: Contains the classes that will be responsible for receiving the requests and returning the responses.
- **model**: Contains the classes that represent the entities and the DTOs.
- **repository**: Contains the classes that will be responsible for the database operations.
- **service**: Contains the classes that will be responsible for the business rules.

## How to run
1. Clone the repository
2. Open the project in your favorite IDE
3. Configure the `application.properties` file with your database settings
4. Run the class `**Application.java`

## Tools
- **Swagger**: `{{BASE_URL}}/swagger-ui/index.html`
- **OpenAPI**: `{{BASE_URL}}/v3/api-docs`
