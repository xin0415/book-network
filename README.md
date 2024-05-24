# book-network
## Overview

Book Social Network is a full-stack application that enables users to manage their book collections and engage with a community of book enthusiasts. It offers features such as user registration, secure email validation, book management (including creation, updating, sharing, and archiving), book borrowing with checks for availability, book return functionality, and approval of book returns. The application ensures security using JWT tokens and adheres to best practices in REST API design. The backend is built with Spring Boot 3 and Spring Security 6, while the frontend is developed using Angular with Bootstrap for styling.

## Features

- User Registration: Users can register for a new account.
- Email Validation: Accounts are activated using secure email validation codes.
- User Authentication: Existing users can log in to their accounts securely.
- Book Management: Users can create, update, share, and archive their books.
- Book Borrowing: Implements necessary checks to determine if a book is borrowable.
- Book Returning: Users can return borrowed books.
- Book Return Approval: Functionality to approve book returns.

#### Class diagram
![Class diagram](screenshots/class-diagram.png)

#### Spring security diagram
![Security diagram](screenshots/security.png)

#### Backend pipeline
![Security diagram](screenshots/be-pipeline.png)

#### Backend pipeline
![Security diagram](screenshots/fe-pipeline.png)

study from: https://www.youtube.com/watch?v=WuPa_XoWlJU
## Technologies Used

### Backend (book-network)

- Spring Boot 3
- Spring Security 6
- JWT Token Authentication
- Spring Data JPA
- JSR-303 and Spring Validation
- OpenAPI and Swagger UI Documentation
- Docker
- GitHub Actions
- Keycloak

### Frontend (book-network-ui)

- Angular
- Component-Based Architecture
- Lazy Loading
- Authentication Guard
- OpenAPI Generator for Angular
- Bootstrap

## Learning Objectives

By following this project, students will learn:

- Designing a class diagram from business requirements
- Implementing a mono repo approach
- Securing an application using JWT tokens with Spring Security
- Registering users and validating accounts via email
- Utilizing inheritance with Spring Data JPA
- Implementing the service layer and handling application exceptions
- Object validation using JSR-303 and Spring Validation
- Handling custom exceptions
- Implementing pagination and REST API best practices
- Using Spring Profiles for environment-specific configurations
- Documenting APIs using OpenAPI and Swagger UI
- Implementing business requirements and handling business exceptions
- Dockerizing the infrastructure
- CI/CD pipeline & deployment

## Run the application
- run the "docker compose up -d" to create contains
- OpenApiConfig used to setup the request, Open this link http://localhost:8088/api/v1/swagger-ui/index.html#/

## Contains the study code

## Setup the docker
https://www.youtube.com/watch?v=UtEHSngmdHA

- one container contains many images

## docker folder contains docker for frontend and backend
# backend part
- run in source director "cd book-network"
- ./mvnw clean package -DskipTests    -package application, it will create book-network-1.0.0.jar file in the target folder
- run in command: docker build -t bsn/bsn:1.0.0 -f docker/backend/Dockerfile .      (remember this .)- bsn is contain name that you want to setup
- version must match to the version in pom.xml file and update the version in docker-compose.yml
- it will create image in docker
- then add new service in docker-compose.yml
- run the docker compose up -d            - to build our image
# frontend part
- setup the frontend Dockerfile
- add nginx.conf file under frontend path
- go the frontend path to build the UI container using: docker build -t bsn/bsn:1.0.0 -f docker/frontend/Dockerfile .
- setup docker-compose.yml for frontend
- run the docker compose up -d to build the image for frontend
- update the cors filter to make backend allow the access from docker

# files if need to change version number
- pom.xml in backend
- docker-compose.yml
- Dockerfile in backend
- build the image and container and remember to change the version number below
  - docker build -t bsn/bsn:1.0.0 -f docker/backend/Dockerfile .  - to build
    - docker compose up -d              -                               to setup the container
- Dockerfile in frontend