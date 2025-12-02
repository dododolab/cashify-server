package io.cashify.stock.application.dto

import io.cashify.stock.domain.Stock
import java.time.LocalDateTime

data class StockResponse(
    val id: Long,
    val ticker: String,
    val companyName: String,
    val exchange: String,
    val industry: String?,
) {
    companion object {
        fun from(stock: Stock): StockResponse {
            return StockResponse(
                id = stock.id,
                ticker = stock.ticker,
                companyName = stock.companyName,
                exchange = stock.exchange,
                industry = stock.industry,
            )
        }
    }
}
