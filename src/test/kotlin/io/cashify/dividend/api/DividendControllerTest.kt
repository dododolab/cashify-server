package io.cashify.dividend.api

import io.cashify.dividend.application.dto.DividendCalculationQuery
import io.cashify.dividend.application.DividendService
import io.cashify.support.exception.GlobalExceptionHandler
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.math.BigDecimal
import java.time.LocalDate

@ExtendWith(MockKExtension::class)
class DividendControllerTest {

    private lateinit var mockMvc: MockMvc

    @MockK
    private lateinit var dividendService: DividendService

    @BeforeEach
    fun setUp() {
        val dividendController = DividendController(dividendService)
        mockMvc = MockMvcBuilders.standaloneSetup(dividendController)
            .setControllerAdvice(GlobalExceptionHandler())
            .build()
    }

    @Nested
    @DisplayName("GET /api/v1/dividends/calculate - 총 배당금 계산 API")
    inner class CalculateDividend {

        @Test
        @DisplayName("요청된 기간 내 총 배당금을 계산한다")
        fun success_calculatesTotalDividendCorrectly() {
            // Given
            val request = DividendCalculationQuery(
                ticker = "AAPL",
                numberOfShares = 100,
                startDate = LocalDate.of(2023, 1, 1),
                endDate = LocalDate.of(2023, 12, 31)
            )
            val expectedResult = BigDecimal("70.00")
            every { dividendService.calculateTotalDividend(request) } returns expectedResult

            // When & Then
            mockMvc.perform(
                get("/api/v1/dividends/calculate")
                    .param("stockTicker", request.ticker)
                    .param("numberOfShares", request.numberOfShares.toString())
                    .param("startDate", request.startDate.toString())
                    .param("endDate", request.endDate.toString())
            )
                .andExpect(status().isOk)
                .andExpect(content().string("70.00"))
        }
    }
}
