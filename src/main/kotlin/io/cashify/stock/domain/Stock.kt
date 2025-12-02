package io.cashify.stock.domain

import io.cashify.support.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(
    name = "stocks",
    uniqueConstraints = [
        UniqueConstraint(name = "uk_ticker", columnNames = ["ticker"]),
    ],
)
class Stock(
    @Column(nullable = false)
    val ticker: String,

    @Column(nullable = false)
    val companyName: String,

    @Column(nullable = false)
    val exchange: String,

    val industry: String?,
) : BaseEntity()
