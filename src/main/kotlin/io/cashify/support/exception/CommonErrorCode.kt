package io.cashify.support.exception

enum class CommonErrorCode(
    override val status: HttpResponseStatus,
    override val message: String,
) : ErrorCode {
    INVALID_INPUT_VALUE(HttpResponseStatus.BAD_REQUEST, "Invalid Input Value"),
    INTERNAL_SERVER_ERROR(HttpResponseStatus.INTERNAL_SERVER_ERROR, "Server Error"),
}
