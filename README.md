# Learinify - E-Learning Platform

**Learnify** is a comprehensive **E-Learning platform** designed to provide a seamless educational experience. Built with a professional, modular architecture using **React** and **Spring Boot**, Learnify features a robust user authentication system with JWT, ensuring secure access. The platform is structured for scalability and future integration with OAuth, supporting a modern and user-friendly learning environment for students and educators.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Folder Structure](#folder-structure)
- [API Documentation](#api-documentation)
- [Testing](#testing)

## Features

- User authentication and authorization (Admin, Instructors, Students)
- Course management (creation, update, deletion)
- Enrollment system for students
- Interactive quizzes and assignments
- Real-time chat functionality
- Responsive design for mobile and desktop
- API documentation with Swagger

## Technologies Used

- **Frontend**:

  - Web Client: (React With Vite Framework)

    - Styles: **Tailwind** , **Material Tailwind** for comprehensive UI components
    - **React Hot Toast** for Toast Notifications/Alerts.
    - **Axios** for Sending API requests

- **Backend**: Spring Boot, Spring Security, JPA, Hibernate
- **Database**: PostgreSQL (or MySQL), Cloudinary (for file storage)
- **Testing**: JUnit, Mockito, Spring Security Test
- **Deployment**: Docker (optional), AWS S3 (for file storage)

## Getting Started

### Prerequisites

Before you begin, ensure you have met the following requirements:

- [Java 21+](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Node.js and npm](https://nodejs.org/en/download/)
- [PostgreSQL](https://www.postgresql.org/download/) or MySQL

### Installation

1. Clone the repository:

```bash
git clone git@github.com:DevAshiZ/Learnify.git
```

2. Navigate to the `backend` directory:

```bash
cd backend
```

3. Create a new PostgreSQL database:

```sql
CREATE DATABASE learnify;
```

4. Update the properties configuration in `src/main/resources/application.properties`:

Properties file:

```properties
# Application Details
spring.application.name=learnify

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/learnify
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA Settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# Server Configuration
server.port=8080

# JWT Configuration
jwt.secret=<your-encoded-secret>

```

5. Run the Spring Boot application:

```bash
mvn spring-boot:run
```

6. Navigate to the `web-client` directory:

```bash
cd web-client
```

7. Create a new `.env` file:

```bash
touch .env
```
8. Add the following environment variables to the `.env` file:

```properties
VITE_REACT_CLOUDINARY_API_KEY=<<your-cloudinary-api-key>>
VITE_REACT_CLOUDINARY_API_SECRET=<<your-cloudinary-api-secret>>
VITE_REACT_CLOUDINARY_CLOUD_NAME=<<your-cloudinary-cloud-name>>
```

8. Install the dependencies:

```bash
npm install
```

9. Start the React application:

- Development mode:

```bash
npm run dev
```

- Production mode:

```bash
npm start
```

## Usage

To use the application, navigate to `http://localhost:3000` in your browser.

## Folder Structure

```bash

learnify
├── backend
│   └── src
│       ├── main
│       │   ├── java
│       │   │    com.learnify.backend
│       │   │       ├── admin
│       │   │       │     ├── controller
│       │   │       │     ├── dao
│       │   │       │     ├── dto
│       │   │       │     ├── repository
│       │   │       │     └── service
│       │   │       ├── common
│       │   │       │     ├── constants
│       │   │       │     ├── exceptions
│       │   │       │     ├── BaseController.java
│       │   │       │     └── BaseResponse.java
│       │   │       ├── masterservice
│       │   │       │     ├── repository
│       │   │       │     ├── dao
│       │   │       │     ├── MasterService.java
│       │   │       │     └── MasterServiceImpl.java
│       │   │       ├── security
│       │   │       │     ├── config
│       │   │       │     ├── dto
│       │   │       │     ├── controller
│       │   │       │     └── service
│       │   │       ├── student
│       │   │       │     ├── controller
│       │   │       │     └── dto
│       │   │       └── teacher
│       │   │             ├── controller
│       │   │             └── dto
│       │   │    
│       │   └── resources
│       │       ├── static
│       │       ├── templates
│       │       └── application.properties
│       └── test
│          
│                         
└── web-client
    ├── index.html
    ├── package.json
    ├── vite.config.js
    ├── tailwind.config.js
    ├── postcss.config.js
    ├── .env
    └── src
        ├── assets
        │   ├── icons
        │   └── images
        ├── components
        │   └── templates
        ├── pages
        │   ├── authentication
        │   ├── privateRoute.jsx
        │   └── common
        ├── utils
        └── controllers


```

## API Documentation

The API documentation is available at `http://localhost:8080/swagger-ui.html`.

## Testing

To run the tests, execute the following command:

```bash
mvn test
```
