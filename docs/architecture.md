# Architecture & Directory Structure

The Cashify Server follows a **Domain-Driven Design (DDD)** inspired package structure, where code is organized by business domain rather than technical layer. This improves maintainability and encapsulation.

## High-Level Architecture

The system is built as a monolithic Spring Boot application, but organized into modular domains to allow for future scalability or potential microservice extraction.

### Layers

1.  **API Layer (`api/`)**: REST Controllers that handle HTTP requests and responses.
2.  **Application Layer (`application/`)**: Services that coordinate domain logic and DTOs for data transfer.
3.  **Domain Layer (`domain/`)**: JPA Entities, Repositories, and core business logic/rules.
4.  **Support Layer (`support/`)**: Shared utilities, base classes, and global exception handlers.

## Package Structure

```text
src/main/kotlin/io/cashify/
├── dividend/           # Dividend Domain
│   ├── api/            # Controllers (e.g., DividendController)
│   ├── application/    # Services and DTOs (e.g., DividendService, DividendResponse)
│   ├── domain/         # Entities and Repositories (e.g., Dividend, DividendRepository)
│   └── exception/      # Domain-specific exceptions
├── stock/              # Stock Domain
│   ├── api/            # Controllers (e.g., StockController)
│   ├── application/    # Services and DTOs (e.g., StockService, StockResponse)
│   ├── domain/         # Entities and Repositories (e.g., Stock, StockRepository)
│   └── exception/      # Domain-specific exceptions
└── support/            # Shared Support Module
    ├── domain/         # BaseEntity, shared value objects
    └── exception/      # GlobalExceptionHandler, common exceptions
```

## Entity Relationship

- **Stock**: The central entity representing a company.
- **Dividend**: Has a `ManyToOne` relationship with `Stock`. Each dividend payout is linked to a specific stock.

## Key Design Patterns

- **Companion Object Mappers**: DTOs often include a `from` method in their companion object to convert entities to DTOs cleanly.
- **Constructor Injection**: All dependencies are injected via constructors as per Spring Boot best practices.
- **Fail-Fast Validation**: Using Jakarta Validation and custom domain-specific exceptions to handle errors early.
