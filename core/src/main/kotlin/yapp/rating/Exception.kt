package yapp.rating

import java.lang.RuntimeException

sealed class YappRatingException(msg: String, cause: Throwable? = null) : RuntimeException(msg, cause)

object InvalidTokenException : YappRatingException("유효하지 않는 토큰 입니다.")
