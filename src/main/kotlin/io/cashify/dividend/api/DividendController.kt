package io.cashify.dividend.api

import io.cashify.dividend.application.DividendService
import io.cashify.dividend.application.dto.DividendCalculationQuery
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.time.LocalDate

@RestController
@RequestMapping("/api/v1/dividends")
class DividendController(
    private val dividendService: DividendService,
) {

    @GetMapping("/calculate")
    fun calculateTotalDividend(
        @RequestParam stockTicker: String,
        @RequestParam numberOfShares: Long,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) startDate: LocalDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) endDate: LocalDate,
    ): ResponseEntity<BigDecimal> {
        val request = DividendCalculationQuery(
            ticker = stockTicker,
            numberOfShares = numberOfShares,
            startDate = startDate,
            endDate = endDate,
        )
        val totalDividend = dividendService.calculateTotalDividend(request)
        return ResponseEntity.ok(totalDividend)
    }
}
