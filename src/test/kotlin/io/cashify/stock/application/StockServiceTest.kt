package io.cashify.stock.application

import io.cashify.stock.application.dto.StockCreateCommand
import io.cashify.stock.domain.Stock
import io.cashify.stock.domain.StockRepository
import io.cashify.stock.exception.StockErrorCode
import io.cashify.support.exception.BusinessException
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class StockServiceTest {

    @MockK
    private lateinit var stockRepository: StockRepository

    @InjectMockKs
    private lateinit var stockService: StockService

    @Nested
    @DisplayName("주식 생성 기능")
    inner class CreateStock {

        @Test
        @DisplayName("새로운 주식 정보를 저장한다")
        fun success_savesNewStock() {
            // Given
            val request = StockCreateCommand("AAPL", "Apple Inc.", "NASDAQ", "Technology")
            val savedStockSlot = slot<Stock>()
            every { stockRepository.findByTicker(request.ticker) } returns null
            every { stockRepository.save(capture(savedStockSlot)) } answers { savedStockSlot.captured }

            // When
            stockService.createStock(request)

            // Then
            val capturedStock = savedStockSlot.captured
            Assertions.assertEquals(request.ticker, capturedStock.ticker)
            Assertions.assertEquals(request.companyName, capturedStock.companyName)
        }

        @Test
        @DisplayName("이미 존재하는 티커로 주식을 생성하려 하면 예외가 발생한다")
        fun fail_existingTickerThrowsException() {
            // Given
            val request = StockCreateCommand("AAPL", "Apple Inc.", "NASDAQ", "Technology")
            val existingStock = Stock(request.ticker, request.companyName, request.exchange, request.industry)
            every { stockRepository.findByTicker(request.ticker) } returns existingStock

            // When & Then
            val exception = assertThrows<BusinessException> {
                stockService.createStock(request)
            }
            Assertions.assertEquals(StockErrorCode.STOCK_ALREADY_EXISTS, exception.errorCode)
        }
    }

    @Nested
    @DisplayName("주식 정보 조회 기능")
    inner class GetStockByTicker {

        @Test
        @DisplayName("유효한 티커로 주식 정보를 조회한다")
        fun success_findsStockByValidTicker() {
            // Given
            val ticker = "AAPL"
            val stock = Stock(ticker, "Apple Inc.", "NASDAQ", "Technology")
            every { stockRepository.findByTicker(ticker) } returns stock

            // When
            val foundStock = stockService.getStockByTicker(ticker)

            // Then
            Assertions.assertEquals(stock.ticker, foundStock.ticker)
            Assertions.assertEquals(stock.companyName, foundStock.companyName)
        }

        @Test
        @DisplayName("존재하지 않는 티커로 조회 시 예외가 발생한다")
        fun fail_nonExistentTickerThrowsException() {
            // Given
            val ticker = "FAIL"
            every { stockRepository.findByTicker(ticker) } returns null

            // When & Then
            val exception = assertThrows<BusinessException> {
                stockService.getStockByTicker(ticker)
            }
            Assertions.assertEquals(StockErrorCode.STOCK_NOT_FOUND, exception.errorCode)
        }
    }
}