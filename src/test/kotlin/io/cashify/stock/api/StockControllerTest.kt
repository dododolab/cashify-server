package io.cashify.stock.api

import io.cashify.stock.application.StockService
import io.cashify.stock.domain.Stock
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockKExtension::class)
class StockControllerTest {

    private lateinit var mockMvc: MockMvc

    @MockK
    private lateinit var stockService: StockService

    @BeforeEach
    fun setUp() {
        val stockController = StockController(stockService)
        mockMvc = MockMvcBuilders.standaloneSetup(stockController)
            .setControllerAdvice(GlobalExceptionHandler())
            .build()
    }

    @Nested
    @DisplayName("GET /api/v1/stocks/{ticker} - 주식 정보 조회 API")
    inner class GetStock {

        @Test
        @DisplayName("티커로 주식 정보를 조회한다")
        fun success() {
            // Given
            val ticker = "AAPL"
            val stock = Stock(ticker, "Apple Inc.", "NASDAQ", "Technology")
            every { stockService.getStockByTicker(ticker) } returns stock

            // When & Then
            mockMvc.perform(get("/api/v1/stocks/{ticker}", ticker))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.ticker").value(ticker))
                .andExpect(jsonPath("$.companyName").value("Apple Inc."))
        }
    }
}
