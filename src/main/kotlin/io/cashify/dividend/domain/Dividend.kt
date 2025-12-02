package io.cashify.dividend.domain

import io.cashify.stock.domain.Stock
import io.cashify.support.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(
    name = "dividends",
    uniqueConstraints = [
        UniqueConstraint(
            name = "uk_stock_ex_dividend_date",
            columnNames = ["stock_id", "exDividendDate"],
        ),
    ],
)
class Dividend(
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stock_id", nullable = false)
    val stock: Stock,

    @Column(nullable = false)
    val exDividendDate: LocalDate,

    @Column(nullable = false)
    val paymentDate: LocalDate,

    @Column(nullable = false)
    val dividend: BigDecimal,

    @Column(nullable = false)
    val currency: String,
) : BaseEntity()
