package io.cashify.stock.api

import io.cashify.stock.application.StockService
import io.cashify.stock.application.dto.StockResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/stocks")
class StockController(
    private val stockService: StockService,
) {

    @GetMapping("/{ticker}")
    fun getStockByTicker(@PathVariable ticker: String): ResponseEntity<StockResponse> {
        val stock = stockService.getStockByTicker(ticker)
        return ResponseEntity.ok(StockResponse.from(stock))
    }
}