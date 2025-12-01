package io.cashify.user.exception

import io.cashify.support.exception.ErrorCode
import io.cashify.support.exception.HttpResponseStatus

enum class UserErrorCode(
    override val status: HttpResponseStatus,
    override val message: String,
) : ErrorCode {
    USER_NOT_FOUND(HttpResponseStatus.NOT_FOUND, "User not found"),
}
