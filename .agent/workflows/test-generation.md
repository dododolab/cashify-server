---
description: Run Test Code Generation
---

# Test Method Generation Workflow

This workflow focuses on generating high-quality unit test methods for specific business logic . If the test class exists, append or provide these methods.

## Guidelines
1. **Target Logic**: Analyze the methods in the target class (e.g., `UserService.kt`).
2. **Isolation**: Use Mockk to isolate the method under test.
3. **Template**: Provide the Given-When-Then code block for each specific scenario.

## Tools
- **JUnit 5**: Test runner.
- **Mockk**: Native Kotlin mocking library.
- **AssertJ**: Fluent assertions.

## Guidelines
1. **Isolation**: Mock all external dependencies (Repositories, other Services).
2. **Behavior**: Use `every { ... } returns ...` or `coEvery { ... } returns ...` for coroutines.
3. **Verification**: Use `verify { ... }` or `confirmVerified(...)` to ensure interactions.
4. **Naming**: Use backticks and descriptive Korean or English names.

## Example (Service Unit Test)
```kotlin
@ExtendWith(MockKExtension::class)
class StockServiceTest {
    @MockK
    lateinit var stockRepository: StockRepository

    @InjectMockKs
    lateinit var stockService: StockService

    @Test
    fun `주식 정보 조회 시 해당 티커가 존재하면 정보를 반환한다`() {
        // given
        val ticker = "AAPL"
        val stock = Stock(ticker = ticker, companyName = "Apple Inc.")
        every { stockRepository.findByTicker(ticker) } returns stock

        // when
        val result = stockService.getStock(ticker)

        // then
        assertThat(result.ticker).isEqualTo(ticker)
        verify { stockRepository.findByTicker(ticker) }
    }
}
```

## Running Tests
// turbo
```powershell
./gradlew test
```