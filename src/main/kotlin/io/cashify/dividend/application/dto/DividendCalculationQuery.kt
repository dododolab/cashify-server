package io.cashify.dividend.application.dto

import java.time.LocalDate

data class DividendCalculationQuery(
    val ticker: String,
    val numberOfShares: Long,
    val startDate: LocalDate,
    val endDate: LocalDate,
)
