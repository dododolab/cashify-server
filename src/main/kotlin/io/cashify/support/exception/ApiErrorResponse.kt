package io.cashify.support.exception

import jakarta.validation.ConstraintViolation
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult

data class ApiErrorResponse(
    val status: Int,
    val message: String,
    val errors: List<FieldError> = emptyList(),
) {
    data class FieldError(
        val field: String,
        val value: String,
        val reason: String,
    ) {
        companion object {
            fun from(bindingResult: BindingResult): List<FieldError> {
                return bindingResult.fieldErrors.map {
                    FieldError(
                        field = it.field,
                        value = it.rejectedValue?.toString() ?: "",
                        reason = it.defaultMessage ?: "No message",
                    )
                }
            }
            fun from(violations: Set<ConstraintViolation<*>>): List<FieldError> {
                return violations.map {
                    val path = it.propertyPath.toString()
                    FieldError(
                        field = path.substring(path.lastIndexOf('.') + 1),
                        value = it.invalidValue?.toString() ?: "",
                        reason = it.message,
                    )
                }
            }
        }
    }

    companion object {
        fun of(e: BusinessException): ResponseEntity<ApiErrorResponse> {
            return ResponseEntity
                .status(e.errorCode.status.code)
                .body(
                    ApiErrorResponse(
                        status = e.errorCode.status.code,
                        message = e.message,
                    ),
                )
        }

        fun of(code: ErrorCode, bindingResult: BindingResult): ResponseEntity<ApiErrorResponse> {
            return ResponseEntity
                .status(code.status.code)
                .body(
                    ApiErrorResponse(
                        status = code.status.code,
                        message = code.message,
                        errors = FieldError.from(bindingResult),
                    ),
                )
        }
        
        fun of(code: ErrorCode, violations: Set<ConstraintViolation<*>>): ResponseEntity<ApiErrorResponse> {
            return ResponseEntity
                .status(code.status.code)
                .body(
                    ApiErrorResponse(
                        status = code.status.code,
                        message = code.message,
                        errors = FieldError.from(violations),
                    ),
                )
        }
    }
}