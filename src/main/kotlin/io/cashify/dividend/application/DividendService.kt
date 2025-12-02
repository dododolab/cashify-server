package io.cashify.dividend.application

import io.cashify.dividend.application.dto.DividendCalculationQuery
import io.cashify.dividend.domain.DividendRepository
import io.cashify.stock.domain.StockRepository
import io.cashify.stock.exception.StockErrorCode
import io.cashify.support.exception.BusinessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class DividendService(
    private val stockRepository: StockRepository,
    private val dividendRepository: DividendRepository,
) {

    @Transactional(readOnly = true)
    fun calculateTotalDividend(query: DividendCalculationQuery): BigDecimal {
        val stock = stockRepository.findByTicker(query.ticker)
            ?: throw BusinessException(StockErrorCode.STOCK_NOT_FOUND)

        val relevantDividends = dividendRepository.findAllByStockAndExDividendDateBetween(
            stock = stock,
            startDate = query.startDate,
            endDate = query.endDate,
        )

        val totalDividendPerShare = relevantDividends.fold(BigDecimal.ZERO) { acc, dividend ->
            acc + dividend.dividend
        }

        return totalDividendPerShare * BigDecimal(query.numberOfShares)
    }
}