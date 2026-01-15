# Kotlin Code Review Guide (Rules & Conventions)

> Purpose  
To review Kotlin code with consistent criteria across **correctness, security, performance, readability, testing, and business logic**.

> Core Principles  
- Clarity over premature or complex optimization  
- Fail fast and fail safely  
- Code reviews are feedback on the code, not the person

---

## 1. Correctness

### 1.1 Null Safety & Optional Alternatives
- Null safety must be guaranteed using Kotlin features such as **nullable types (?), Elvis (?:), and safe-calls (?.)**
- External inputs, DB query results, and Map access results must be **modeled as nullable** or guarded explicitly
- Avoid using Java `Optional` directly in Kotlin; prefer **nullable returns** or **Result/Sealed types**
- Use **emptyList()/emptySet()/emptyMap()** instead of null for collections
- Equality checks must be NPE-safe  
  - `a == b` is null-safe, but be cautious with platform types

### 1.2 Conditionals / Loops
- Collection/array access must be **bounds-safe** (e.g., `getOrNull`, `firstOrNull`)
- Loop logic must have **clear termination/exit conditions**
- Simplify complex conditions using **guard clauses (early returns)**
- Ensure no missing branches in `when` expressions  
  - Use **exhaustive handling** with sealed classes or enums

### 1.3 Exception Handling
- Do not ignore exceptions or leave empty `catch` blocks
- Exception handling strategy must be clear: **logging vs. propagation**
- Release resources safely using `use {}` (AutoCloseable)
- Clearly distinguish **business exceptions** from **system exceptions**  
  - Business: domain errors, validation failures  
  - System: I/O, DB, external communication issues

### 1.4 Concurrency
- Access to shared resources must be thread-safe
- Be cautious when mutating state in `object` singletons or `companion object`
- Do not share thread-unsafe objects globally
- When using coroutines, ensure consistent policies for `Dispatchers`, `SupervisorJob`, and cancellation propagation

---

## 2. Security

### 2.1 Input Validation
- All external inputs must be validated
- Avoid direct entity binding; use DTOs
- Use Bean Validation annotations or explicit validation logic
- Ensure default values (`?:`) do not bypass validation unintentionally

### 2.2 Injection
- Do not construct SQL via string concatenation
- Use JPA, QueryDSL, or parameter binding
- Do not log raw user input without masking or normalization

### 2.3 XSS
- Escape all user input that is rendered in output
- Ensure no script injection is possible in HTML/template rendering

### 2.4 Authentication / Authorization
- Always check **authorization**, not just authentication
- Sensitive/admin APIs require explicit permission checks
- When using SecurityContext/Principal, handle null or anonymous users safely

### 2.5 Sensitive Information
- Do not hardcode secrets, tokens, or passwords
- Do not include personal or sensitive data in logs or exception messages
- Use environment variables or external secret stores for configuration

### 2.6 External APIs
- Timeouts must always be configured
- Retry logic must apply **only to idempotent requests**
- Defend against Path Traversal in file/path handling

---

## 3. Performance

### 3.1 Database
- Avoid N+1 query issues
- Use pagination or limits for large result sets
- Queries must account for proper indexing

### 3.2 Loops / Algorithms
- Remove unnecessary nested loops
- Use `sequence {}` / `asSequence()` when lazy evaluation is beneficial
- Balance readability and performance when chaining `map`/`filter`
- Avoid repetitive string concatenation; use `buildString {}` or `StringBuilder`

### 3.3 Memory / Resources
- Process large data sets using streaming or sequences
- Prevent leaks of connections, threads, or file resources (`use {}`)
- Caches must have explicit expiration and invalidation strategies

### 3.4 External Calls
- Consider caching or batching for frequent external calls
- Ensure network failures do not cascade into system-wide outages
  (e.g., timeouts, circuit breakers, fallbacks)

---

## 4. Style & Readability

### 4.1 Naming
- Classes/Interfaces: PascalCase
- Functions/Variables: camelCase
- Constants: UPPER_SNAKE_CASE (`const val`)
- Boolean names must be expressive (`is`, `has`, `can`, etc.)

### 4.2 Structure
- Functions should follow the Single Responsibility Principle
- Split overly long functions
- Avoid deep nesting (be cautious with excessive `let/run/apply/also`)
- Separate domain logic from infrastructure logic
- Use extension functions only when domain meaning is clear; avoid excessive global extensions

### 4.3 Immutability
- Prefer `val`; use `var` only when mutation is required
- Extract magic numbers/strings into constants or enums/sealed types
- Use appropriate types for dates and money (e.g., `BigDecimal`, `Instant`, `LocalDateTime`)
- For data classes, ensure equality and hashing semantics match intent

### 4.4 Comments
- Comments should explain **why**, not what
- Remove redundant or copied comments
- TODO/FIXME must include justification or an issue reference

### 4.5 Formatting
- Follow team formatters (ktlint/detekt/IDE settings)
- Remove unused imports and duplicate code
- Avoid `!!` in principle; if unavoidable, the rationale must be explicit

---

## 5. Testing

### 5.1 Test Presence
- All changed logic must be covered by tests
- Bug fixes must include regression tests

### 5.2 Test Coverage
- Include both normal and edge cases
- Validate nulls, boundary values, and authorization failures
- When using coroutines, control scheduling/time deterministically (e.g., `runTest`)

### 5.3 Test Quality
- Follow the Given-When-Then structure
- Do not depend on implementation details
- Mock or use test doubles for external dependencies

---

## 6. Business Logic

### 6.1 Requirements
- Business requirements must be implemented accurately
- Exception and failure scenarios must be clearly defined

### 6.2 Data Consistency
- State transitions must remain consistent
- Defenses against duplicate requests or reprocessing must exist
- Idempotency logic must be explicit and intention-revealing, not hidden by Kotlin syntax

### 6.3 Transactions
- Transaction boundaries must be appropriate
- Avoid long-running external API calls inside transactions
- Verify transaction boundaries are preserved when using suspend functions/coroutines

### 6.4 Error Handling
- Distinguish domain errors from system errors
- Separate user-facing messages from internal log messages
- When using Result/Either/Sealed designs, failure representation and propagation must be consistent

---

## 7. Observability (Operations)

- Key logic must produce logs
- Log levels must be appropriate
- Sensitive data must be masked
- Logs must include identifiers to support incident analysis
- Ensure trace/correlation IDs are propagated across coroutines and async boundaries

---

## 8. PR Author Checklist

- [ ] The purpose and scope of changes are clear
- [ ] Tests have been added or updated
- [ ] Security and performance impacts have been reviewed
- [ ] Logging and operational impact have been considered

---
