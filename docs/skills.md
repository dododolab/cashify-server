# Tech Stack & Skills

Cashify Server is built with a modern backend tech stack centered around Kotlin and Spring Boot, focusing on performance, type safety, and developer productivity.

## Core Technologies

| Category | Technology | Version | Description |
| :--- | :--- | :--- | :--- |
| **Language** | Kotlin | 2.2.21 | Modern JVM language with null safety and coroutines support. |
| **Framework** | Spring Boot | 4.0.0 | Rapid application development framework. |
| **Build Tool** | Gradle (Kotlin DSL) | - | Flexible build automation. |
| **JDK** | Java | 24 | Latest Java features and performance improvements. |
| **Linter** | KtLint | 14.0.1 | Anti-bikeshedding linter and formatter for Kotlin. |
| **Testing** | Mockk | 1.14.6 | Native Kotlin library for mocking dependencies. |

## Persistence & Data

- **MySQL**: Production relational database for persistent storage.
- **H2**: In-memory database used for fast and isolated testing.
- **Spring Data JPA (Hibernate)**: ORM for mapping Kotlin objects to database tables.
- **Jakarta Validation**: Bean validation for data integrity at the API and entity levels.

## Testing & Quality

- **JUnit 5**: The foundation for writing and running tests.
- **Mockk**: Native Kotlin library for mocking dependencies.
- **AssertJ**: Fluent assertions for readable and expressive tests.
- **KtLint**: Anti-bikeshedding linter and formatter for Kotlin.

## Infrastructure

- **Docker Compose**: Used to spin up local development environments (e.g., MySQL).
- **GitHub Actions**: (Planned/Recommended) For CI/CD pipelines.

## Skills Required for Contributors

1. **Kotlin Mastery**: Understanding of extension functions, data classes, null safety, and scope functions (`let`, `run`, `apply`, etc.).
2. **Spring Boot Ecosystem**: Experience with Spring MVC, Spring Data JPA, and Dependency Injection.
3. **Domain-Driven Design (DDD)**: Familiarity with organizing code by domain rather than layer.
4. **Testing Excellence**: Proficiency in writing unit and integration tests using Mockk and AssertJ.
