package yapp.rating

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ErrorResponse = e.toResponse()

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BolRatingException::class)
    fun handleException(e: BolRatingException): ErrorResponse = e.toResponse()

    private fun Exception.toResponse(): ErrorResponse {
        return ErrorResponse(message ?: DEFAULT_MESSAGE)
    }

    data class ErrorResponse(
        val message: String,
    )

    companion object {
        const val DEFAULT_MESSAGE = "에러가 발생했습니다. 다시 시도해주세요."
    }
}
