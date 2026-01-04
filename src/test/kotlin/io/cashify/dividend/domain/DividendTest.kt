package io.cashify.dividend.domain

import io.cashify.stock.domain.Stock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate

@DisplayName("배당금 도메인 모델")
class DividendTest {

    private lateinit var stock: Stock

    @BeforeEach
    fun setUp() {
        stock = Stock(
            ticker = "MSFT",
            companyName = "Microsoft Corp.",
            exchange = "NASDAQ",
            industry = "Technology",
        )
    }

    @Nested
    @DisplayName("배당금 객체 생성")
    inner class DividendCreation {

        @Test
        @DisplayName(" 모든 필수 정보로 배당금 객체를 생성할 수 있다")
        fun `Dividend can be instantiated with all essential information`() {
            // Given
            val exDividendDate = LocalDate.of(2023, 10, 25)
            val paymentDate = LocalDate.of(2023, 11, 10)
            val dividendAmount = BigDecimal("0.68")
            val currency = "USD"

            // When
            val dividend = Dividend(
                stock = stock,
                exDividendDate = exDividendDate,
                paymentDate = paymentDate,
                dividend = dividendAmount,
                currency = currency,
            )

            // Then
            assertNotNull(dividend)
            assertEquals(stock, dividend.stock)
            assertEquals(exDividendDate, dividend.exDividendDate)
            assertEquals(paymentDate, dividend.paymentDate)
            assertEquals(dividendAmount, dividend.dividend)
            assertEquals(currency, dividend.currency)
        }
    }
}
