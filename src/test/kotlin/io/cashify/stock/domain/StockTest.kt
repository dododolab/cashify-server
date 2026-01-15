package io.cashify.stock.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions.assertSoftly
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
            assertSoftly {
                assertThat(stock).isNotNull()
                assertThat(stock.ticker).isEqualTo(ticker)
                assertThat(stock.companyName).isEqualTo(companyName)
                assertThat(stock.exchange).isEqualTo(exchange)
                assertThat(stock.industry).isEqualTo(industry)
            }
        }
    }
}
