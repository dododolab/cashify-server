package io.cashify.dividend.application

import io.cashify.dividend.application.dto.DividendCalculationQuery
import io.cashify.dividend.domain.Dividend
import io.cashify.dividend.domain.DividendRepository
import io.cashify.stock.domain.Stock
import io.cashify.stock.domain.StockRepository
import io.cashify.stock.exception.StockErrorCode
import io.cashify.support.exception.BusinessException
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.time.LocalDate

@ExtendWith(MockKExtension::class)
class DividendServiceTest {

    @MockK
    private lateinit var stockRepository: StockRepository

    @MockK
    private lateinit var dividendRepository: DividendRepository

    @InjectMockKs
    private lateinit var dividendService: DividendService

    private lateinit var stock: Stock
    private val ticker = "AAPL"

    @BeforeEach
    fun setUp() {
        stock = Stock(
            ticker = ticker,
            companyName = "Apple Inc.",
            exchange = "NASDAQ",
            industry = "Technology"
        )
    }

    @Nested
    @DisplayName("총 배당금 계산 기능")
    inner class CalculateTotalDividend {

        @Test
        @DisplayName("요청된 기간 내 배당금을 계산한다")
        fun success_calculatesTotalDividendCorrectly() {
            // Given
            val startDate = LocalDate.of(2023, 1, 1)
            val endDate = LocalDate.of(2023, 12, 31)

            val relevantDividends = listOf(
                Dividend(stock, LocalDate.of(2023, 2, 1), LocalDate.of(2023, 2, 15), BigDecimal("0.23"), "USD"),
                Dividend(stock, LocalDate.of(2023, 5, 1), LocalDate.of(2023, 5, 15), BigDecimal("0.23"), "USD"),
                Dividend(stock, LocalDate.of(2023, 8, 1), LocalDate.of(2023, 8, 15), BigDecimal("0.24"), "USD")
            )

            every { stockRepository.findByTicker(ticker) } returns stock
            every { dividendRepository.findAllByStockAndExDividendDateBetween(stock, startDate, endDate) } returns relevantDividends

            val request = DividendCalculationQuery(
                ticker = ticker,
                numberOfShares = 100,
                startDate = startDate,
                endDate = endDate
            )

            // When
            val totalDividend = dividendService.calculateTotalDividend(request)

            // Then
            Assertions.assertEquals(BigDecimal("70.00"), totalDividend) // (0.23 + 0.23 + 0.24) * 100
        }

        @Test
        @DisplayName("주식이 존재하지 않으면 예외가 발생한다")
        fun fail_stockNotFoundThrowsException() {
            // Given
            val nonExistentTicker = "FAIL"
            every { stockRepository.findByTicker(nonExistentTicker) } returns null

            val request = DividendCalculationQuery(
                ticker = nonExistentTicker,
                numberOfShares = 100,
                startDate = LocalDate.of(2023, 1, 1),
                endDate = LocalDate.of(2023, 12, 31)
            )

            // When & Then
            val exception = assertThrows<BusinessException> {
                dividendService.calculateTotalDividend(request)
            }
            Assertions.assertEquals(StockErrorCode.STOCK_NOT_FOUND, exception.errorCode)
        }

        @Test
        @DisplayName("요청된 기간 내 배당금이 없으면 0을 반환한다")
        fun warning_noDividendsInDateRangeReturnsZero() {
            // Given
            val startDate = LocalDate.of(2023, 1, 1)
            val endDate = LocalDate.of(2023, 12, 31)

            every { stockRepository.findByTicker(ticker) } returns stock
            every { dividendRepository.findAllByStockAndExDividendDateBetween(stock, startDate, endDate) } returns emptyList()

            val request = DividendCalculationQuery(
                ticker = ticker,
                numberOfShares = 100,
                startDate = startDate,
                endDate = endDate
            )

            // When
            val totalDividend = dividendService.calculateTotalDividend(request)

            // Then
            Assertions.assertEquals(BigDecimal("0"), totalDividend.stripTrailingZeros())
        }
    }
}