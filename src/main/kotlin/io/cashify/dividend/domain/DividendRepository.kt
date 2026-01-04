package io.cashify.dividend.domain

import io.cashify.stock.domain.Stock
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface DividendRepository : JpaRepository<Dividend, Long> {
    fun findAllByStockAndExDividendDateBetween(
        stock: Stock,
        startDate: LocalDate,
        endDate: LocalDate,
    ): List<Dividend>
}
