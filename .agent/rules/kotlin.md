# Kotlin Development Rules

These rules define the coding standards and practices for the Cashify Server project. Antigravity should strictly follow these when generating or modifying code. For more detailed architectural and tech stack information, refer to the [docs/](docs/) directory.

## 1. Code Style (KtLint)
- Follow the standard Kotlin coding conventions as enforced by ktlint.
- Indentation: 4 spaces.
- Use trailing commas where applicable.
- Max line length: 120 characters.
- Avoid unnecessary semicolons.

## 2. Testing Standards
- **Framework**: JUnit 5.
- **Mocking**: Mockk (`io.mockk:mockk`).
- **Assertions**: AssertJ (`org.assertj:assertj-core`).
  - Use `org.assertj.core.api.Assertions.assertThat`.
  - Prefer `isEqualTo()`, `hasSize()`, `isTrue()`, etc.
- **Naming**: Use backticks for test method names to describe the behavior in plain English or Korean (e.g., `` `should return stock details when ticker exists` ``).
- **Structure**: Use the Given-When-Then pattern.

## 3. Implementation Details
- **DTOs**: Use DTOs for API boundaries. Use companion object `from` methods to map from entities.
- **Null Safety**: Leverage Kotlin's null safety. Avoid `!!` unless absolutely necessary.
- **Validation**: Use Jakarta Validation annotations (`@NotBlank`, `@NotNull`, etc.).
- **Response**: Use `ResponseEntity` for controller responses.

## 4. Workflows
- Always run `./gradlew ktlintCheck` before completing a task.
- Ensure all relevant tests pass with `./gradlew test`.
