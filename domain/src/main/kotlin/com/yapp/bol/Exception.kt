package com.yapp.bol

import java.lang.RuntimeException

sealed class BolRatingException(
    val code: String,
    override val message: String,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)

sealed class LoginException(code: String, msg: String, cause: Throwable? = null) : BolRatingException(code, msg, cause)

class SocialLoginFailedException(cause: Throwable? = null) : LoginException("Auth001", "로그인이 실패했습니다.", cause)
object InvalidTokenException : LoginException("Auth002", "유효하지 않는 토큰 입니다.")
object ExpiredTokenException : LoginException("Auth003", "만료된 토큰 입니다.")
