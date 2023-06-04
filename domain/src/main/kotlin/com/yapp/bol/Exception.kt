package com.yapp.bol

import java.lang.RuntimeException

sealed class BolRatingException(
    val code: String,
    val status: Int,
    override val message: String,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)

sealed class LoginException(code: String, msg: String, cause: Throwable? = null) : BolRatingException(code, 400, msg, cause)

class SocialLoginFailedException(cause: Throwable? = null) : LoginException("Auth001", "로그인이 실패했습니다.", cause)
object InvalidTokenException : LoginException("Auth002", "유효하지 않는 토큰 입니다.")
object ExpiredTokenException : LoginException("Auth003", "만료된 토큰 입니다.")

object InvalidRequestException : BolRatingException("BOL001", 400, "유효하지 않은 요청입니다.")
object IllegalFileStateException : BolRatingException("BOL002", 500, "요청한 파일의 Status가 올바르지 않습니다.")
