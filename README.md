# Payment Service

Payment Service is a Java application developed using Spring Boot (version 3.2.2) and Java 21. It serves as a backend service for handling payment management, it gets the student from student service and fee from fee service against student grade and process the payment and after send request document service to generate receipt. The application utilizes an H2 database for data storage.

## Technologies

- Java 21
- Spring Boot 3.2.2
- H2 Database
- JUnit
- Swagger for API documentation
- Spring Boot Actuator for monitoring and managing application

## Features

1. **Collect Fee:** This API collects the student fee, gets the data from student and fee service, process the payment and communities with document service to generate the receipt

4. **Swagger Documentation:**
    - API documentation is available through Swagger for easy exploration and integration.
    - Accessible at [http://localhost:8083/payment/swagger-ui/index.html#/](http://localhost:8083/payment/swagger-ui/index.html#/).
### Spring Boot Actuator

- Monitor and manage the application in production with Spring Boot Actuator.
- Endpoints include health, metrics, info, and more.
- Accessible at [http://localhost:8083/payment/actuator](http://localhost:8083/payment/actuator).

## Unit Test Cases

- JUnit test cases are implemented to ensure the reliability and correctness of the application.

## Setup

1. Clone the repository.
2. Configure your IDE or build tool for a Spring Boot application.
3. Run the application and access the Swagger documentation to explore the APIs.


## Endpoints

- **Payments:**
    - POST `/api/payments` - This API collects the student fee, gets the data from student and fee service, process the payment and communities with document service to generate the receipt
# Getting Started

To get started with the project, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/Awais1991/payment-service.git

2. Build Application:

   ```bash
   mvn clean install

3. Run Application:

   ```bash
   java -jar fee-0.0.1-SNAPSHOT.jar

4. Access Swagger:

   ```bash
   http://localhost:8083/payment/swagger-ui/index.html#/