package io.cashify.support.exception

import jakarta.validation.ConstraintViolationException
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValid(e: MethodArgumentNotValidException): ResponseEntity<ApiErrorResponse> {
        logger.warn(e.message, e)
        return ApiErrorResponse.of(CommonErrorCode.INVALID_INPUT_VALUE, e.bindingResult)
    }

    @ExceptionHandler(BindException::class)
    fun handleBindException(e: BindException): ResponseEntity<ApiErrorResponse> {
        logger.warn(e.message, e)
        return ApiErrorResponse.of(CommonErrorCode.INVALID_INPUT_VALUE, e.bindingResult)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(e: ConstraintViolationException): ResponseEntity<ApiErrorResponse> {
        logger.warn(e.message, e)
        return ApiErrorResponse.of(CommonErrorCode.INVALID_INPUT_VALUE, e.constraintViolations)
    }

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException): ResponseEntity<ApiErrorResponse> {
        logger.warn("BusinessException: ${e.message}", e)
        return ApiErrorResponse.of(e)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiErrorResponse> {
        logger.error("Exception: ${e.message}", e)
        val businessException = BusinessException(CommonErrorCode.INTERNAL_SERVER_ERROR)
        return ApiErrorResponse.of(businessException)
    }
}
