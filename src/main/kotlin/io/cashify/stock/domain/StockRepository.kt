package io.cashify.stock.domain

import org.springframework.data.jpa.repository.JpaRepository

interface StockRepository : JpaRepository<Stock, Long> {
    fun findByTicker(ticker: String): Stock?
}
