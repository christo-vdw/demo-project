# Demo Project

This is a demo project illustrate the use of a few popular font end and back end technologies to build a simple web application.

The app consists of a Spring Boot backend that serves a Vue frontend.

On startup the backend parses a CSV located in the resources into an in memory DB.

The backend exposes functionality to the client via a Rest API. The client can perform CRUD operations on the dataset or access a derived  
agrregation that gets calculated upon request.

## Technologies

### Frontend

Vue
npm
ts
Vuex
Vuetify
Nightwatch

### Backend

Swagger
Spring Boot
JPA
Maven
H2

## Building

The build is configured using Maven.

Maven is utilised to build the backend and invokes npm build and package the frontend.

Nightwatch is used for E2E testing.

To run the build with the tests included:

`mvn clean install`

Running the built app:

`java -jar ./backend/target/backend-0.0.1-SNAPSHOT.jar`

and navigate to:

`http://localhost:8080/#/`

## Links

While the application is running the API documention as well as the in memory DB can be accessed directly.

### API Documentation

- http://localhost:8080/swagger-ui.htm

### H2

- http://localhost:8080/h2-console
  - Credentials:
    - JDBC URL: 'jdbc:h2:mem:testdb'
    - User Name: 'sa'
    - Password: 'password'

## TODO

- Implement CRUD operations on the frontend.
- Expand testing.
- Add surefire and ensure that backend unit tests are running with build.
- Add Licence
  
## Bugs & Issues

- If the E2E testing fails the server is not killed properly and subsequent builds can't run the test again. To get around this the process has to be killed manually.
