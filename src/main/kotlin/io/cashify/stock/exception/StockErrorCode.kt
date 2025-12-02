package io.cashify.stock.exception

import io.cashify.support.exception.ErrorCode
import io.cashify.support.exception.HttpResponseStatus
import org.springframework.http.HttpStatus

enum class StockErrorCode(
    override val status: HttpResponseStatus,
    override val message: String,
) : ErrorCode {
    STOCK_NOT_FOUND(HttpResponseStatus.NOT_FOUND, "Stock not found"),
    STOCK_ALREADY_EXISTS(HttpResponseStatus.CONFLICT, "Stock already exists"),
}
