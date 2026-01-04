package io.cashify.stock.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("주식 도메인 모델")
class StockTest {

    @Nested
    @DisplayName("주식 객체 생성")
    inner class StockCreation {

        @Test
        @DisplayName("필수 정보로 주식 객체를 생성할 수 있다")
        fun `Stock can be instantiated with essential information`() {
            // Given
            val ticker = "AAPL"
            val companyName = "Apple Inc."
            val exchange = "NASDAQ"
            val industry = "Technology"

            // When
            val stock = Stock(
                ticker = ticker,
                companyName = companyName,
                exchange = exchange,
                industry = industry,
            )

            // Then
            assertNotNull(stock)
            assertEquals(ticker, stock.ticker)
            assertEquals(companyName, stock.companyName)
            assertEquals(exchange, stock.exchange)
            assertEquals(industry, stock.industry)
        }
    }
}
