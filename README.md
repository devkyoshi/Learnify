# Learinify - E-Learning Platform

Learnify is a comprehensive E-Learning platform designed to provide a seamless educational experience. Built with a professional, modular architecture using React and Spring Boot, Learnify features a robust user authentication system with JWT, ensuring secure access. The platform is structured for scalability and future integration with OAuth, supporting a modern and user-friendly learning environment for students and educators.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Usage](#usage)
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

- **Frontend**: React, Redux, Axios, Tailwind CSS, Material Tailwind
- **Backend**: Spring Boot, Spring Security, JPA, Hibernate
- **Database**: PostgreSQL (or MySQL)
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

4. Update the database configuration in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/learnify
spring.datasource.username=your-username
spring.datasource.password=your-password
```

5. Run the Spring Boot application:

```bash 
mvn spring-boot:run
```

6. Navigate to the `frontend` directory:

```bash
cd frontend
```

7. Install the dependencies:

```bash
npm install
```

8. Start the React application:

- Development mode:
```bash
npm run dev
```

- Production mode:
```bash
npm start
```

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

## Usage

To use the application, navigate to `http://localhost:3000` in your browser.

## API Documentation

The API documentation is available at `http://localhost:8080/swagger-ui.html`.

## Testing

To run the tests, execute the following command:

```bash
mvn test
```

