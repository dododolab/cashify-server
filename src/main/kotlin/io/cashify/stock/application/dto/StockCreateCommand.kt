package io.cashify.stock.application.dto

data class StockCreateCommand(
    val ticker: String,
    val companyName: String,
    val exchange: String,
    val industry: String?,
)
