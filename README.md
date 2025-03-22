# Portfolio API with Spring Boot and PostgreSQL

A comprehensive portfolio management application built using Spring Boot and PostgreSQL/CockroachDB, designed to showcase professional experience, skills, education, and projects.

## Features

- RESTful API for accessing portfolio data
- Data model supporting multiple portfolio sections:
  - Personal Information
  - Education
  - Work Experience
  - Projects
  - Skills (organized by categories)
  - Languages
  - Interests
- Frontend portfolio viewer/dashboard
- PostgreSQL/CockroachDB integration with secure SSL connection
- Responsive design using Bootstrap 5

## Technologies Used

- **Backend**:
  - Java 17
  - Spring Boot 3.1.5
  - Spring Data JPA
  - Hibernate ORM
  - HikariCP Connection Pool

- **Database**:
  - PostgreSQL/CockroachDB
  - JDBC

- **Frontend**:
  - HTML5
  - CSS3
  - JavaScript
  - Bootstrap 5
  - Bootstrap Icons

- **Build Tool**:
  - Maven

## Project Structure
```
└── mahii1972-portfolio-springboot/
    ├── pom.xml
    └── src/
        └── main/
            ├── java/
            │   └── com/
            │       ├── portfolio/
            │       │   ├── PortfolioApplication.java
            │       │   ├── controller/
            │       │   ├── model/
            │       │   ├── repository/
            │       │   └── service/
            │       └── yourpackage/
            │           ├── config/
            │           └── util/
            └── resources/
                ├── application.properties
                └── static/
```

## API Endpoints

- `GET /api/portfolio` - Retrieve basic portfolio information
- `GET /api/portfolio/full` - Retrieve complete portfolio data
- `GET /api/portfolio/raw` - Retrieve raw portfolio data

## Database Schema

The application uses the following main tables:
- `personal_info` - Personal details
- `education` - Educational history
- `experience` - Work experience
- `projects` - Project showcase
- `skill_categories` - Skill groupings
- `skills` - Individual skills linked to categories
- `interests` - Personal interests
- `languages` - Language proficiencies

## Setup and Configuration

### Prerequisites

- JDK 17 or later
- Maven
- PostgreSQL or CockroachDB instance

### Environment Variables

The application uses the following environment variables:
- `JDBC_URL` - Database connection URL
- `DB_USERNAME` - Database username
- `DB_PASSWORD` - Database password
- `CERT` - (Optional) SSL certificate content for secure database connection

### Running the Application

1. Clone the repository
```
git clone https://github.com/mahii1972/portfolio-springboot.git
cd portfolio-springboot
```

2. Set environment variables for database connection

3. Build the application
```
mvn clean install
```

4. Run the application
```
mvn spring-boot:run
```

5. Access the application in a web browser
```
http://localhost:8080
```

## Database Connection

The application includes a custom `DatabaseConfig` class that configures a HikariCP connection pool for optimal database performance. It also includes a `PostgreSQLCertificateInitializer` to manage SSL certificates for secure database connections.

## Frontend Dashboard

The frontend portfolio dashboard provides a visually appealing presentation of all portfolio data with:
- Responsive layout for all devices
- Interactive cards with hover effects
- Timeline-style presentation for education and experience
- Categorized skill sections
- Project showcase with technology badges
- Social media and contact links

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the LICENSE file for details.