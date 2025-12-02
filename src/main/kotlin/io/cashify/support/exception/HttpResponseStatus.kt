package io.cashify.support.exception

enum class HttpResponseStatus(
    val code: Int,
    val reason: String,
) {
    // 4xx
    BAD_REQUEST(400, "Bad Request"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    CONFLICT(409, "Conflict"),

    // 5xx
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
}
