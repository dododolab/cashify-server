package io.cashify.support.exception

interface ErrorCode {
    val status: HttpResponseStatus
    val message: String
}
