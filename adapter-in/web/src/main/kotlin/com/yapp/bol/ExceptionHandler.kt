package com.yapp.bol

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @Order(value = Ordered.LOWEST_PRECEDENCE - 10)
    @ExceptionHandler(BolRatingException::class)
    fun handleException(e: BolRatingException): ResponseEntity<ErrorResponse> = e.toResponse()

    @Order(value = Ordered.LOWEST_PRECEDENCE)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ErrorResponse = ErrorResponse(
        "UNKNOWN",
        e.message ?: DEFAULT_MESSAGE,
    )

    private fun BolRatingException.toResponse(): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(this.status).body(ErrorResponse(code, message))


    data class ErrorResponse(
        val code: String,
        val message: String,
    )

    companion object {
        const val DEFAULT_MESSAGE = "에러가 발생했습니다. 다시 시도해주세요."
    }
}
