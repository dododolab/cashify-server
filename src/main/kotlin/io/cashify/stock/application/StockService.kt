package io.cashify.stock.application

import io.cashify.stock.application.dto.StockCreateCommand
import io.cashify.stock.domain.Stock
import io.cashify.stock.domain.StockRepository
import io.cashify.stock.exception.StockErrorCode
import io.cashify.support.exception.BusinessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StockService(
    private val stockRepository: StockRepository,
) {
    @Transactional
    fun createStock(command: StockCreateCommand): Stock {
        if (stockRepository.findByTicker(command.ticker) != null) {
            throw BusinessException(StockErrorCode.STOCK_ALREADY_EXISTS)
        }

        val stock = Stock(
            ticker = command.ticker,
            companyName = command.companyName,
            exchange = command.exchange,
            industry = command.industry
        )
        return stockRepository.save(stock)
    }

    @Transactional(readOnly = true)
    fun getStockByTicker(ticker: String): Stock {
        return stockRepository.findByTicker(ticker)
            ?: throw BusinessException(StockErrorCode.STOCK_NOT_FOUND)
    }
}