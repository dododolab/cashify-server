package io.cashify.support.exception

enum class HttpResponseStatus(
    val code: Int,
) {
    // 4xx
    UNAUTHORIZED(401),
    BAD_REQUEST(400),
    FORBIDDEN(403),
    NOT_FOUND(404),

    // 5xx
    INTERNAL_SERVER_ERROR(500),
}
