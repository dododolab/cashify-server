# Cashify Server

## Project Overview

**Cashify Server** is a backend application built with **Kotlin** and **Spring Boot**, designed to manage financial data related to stocks and dividends. It provides a RESTful API for querying stock information and tracking dividend distributions.

## Tech Stack

*   **Language:** Kotlin (JVM)
*   **Framework:** Spring Boot 4.0.0
*   **Build Tool:** Gradle (Kotlin DSL)
*   **JDK:** Java 24
*   **Database:**
    *   **Runtime:** MySQL
    *   **Test:** H2 (In-memory)
*   **ORM:** Spring Data JPA (Hibernate)
*   **Testing:** JUnit 5, Mockk
*   **Linter:** KtLint

## Architecture

The project follows a domain-driven package structure:

*   `src/main/kotlin/io/cashify/`
    *   `stock/`: Domain module for Stock management.
        *   `api/`: REST Controllers (e.g., `StockController`).
        *   `application/`: Service layer and DTOs.
        *   `domain/`: JPA Entities (e.g., `Stock`) and Repositories.
        *   `exception/`: Domain-specific exceptions.
    *   `dividend/`: Domain module for Dividend management.
        *   Similar structure to `stock`.
    *   `support/`: Shared utilities and base classes.
        *   `domain/`: `BaseEntity` (likely contains common fields like ID, timestamps).
        *   `exception/`: Global exception handling (`GlobalExceptionHandler`).

## Key Entities

*   **Stock:** Represents a publicly traded company.
    *   Fields: `ticker`, `companyName`, `exchange`, `industry`.
    *   Constraints: Unique ticker.
*   **Dividend:** Represents a dividend payout for a stock.
    *   Fields: `stock` (ManyToOne), `exDividendDate`, `paymentDate`, `dividend` (amount), `currency`.
    *   Constraints: Unique combination of stock and ex-dividend date.

## Build and Run

### Prerequisites
*   JDK 24
*   Docker (optional, for running MySQL via Compose)

### Commands

**Build:**
```bash
./gradlew build
```

**Run Application:**
```bash
./gradlew bootRun
```

**Run Tests:**
```bash
./gradlew test
```

**Lint Code:**
```bash
./gradlew ktlintCheck
```

**Format Code:**
```bash
./gradlew ktlintFormat
```

## Configuration

*   **Main Configuration:** `src/main/resources/application.yml`
    *   Default profile uses MySQL on `localhost:3306` (database `cashify`).
*   **Docker Compose:** `compose.yaml` (available for development environment setup).

## Development Conventions

*   **REST API:** V1 APIs are prefixed with `/api/v1`.
*   **DTOs:** Use DTOs for API requests and responses (e.g., `StockResponse`). `from` companion objects/methods are used for mapping Entities to DTOs.
*   **Validation:** Jakarta Validation is used.
*   **Nullability:** Kotlin's null safety is leveraged.
## Project Documentation

Detailed information about the project can be found in the `docs/` directory:

- [Tech Stack & Skills](docs/skills.md): Detailed explanation of the technologies and required skills.
- [Architecture & Structure](docs/architecture.md): High-level overview of the system architecture and package structure.
