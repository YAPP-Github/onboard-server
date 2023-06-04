package com.yapp.bol

import java.lang.RuntimeException

sealed class BolRatingException(
    val code: String,
    val status: Int,
    override val message: String,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)

sealed class AuthException(code: String, status: Int, msg: String, cause: Throwable? = null) : BolRatingException(code, status, msg, cause)
class SocialLoginFailedException(cause: Throwable? = null) : AuthException("Auth001", 400, "로그인이 실패했습니다.", cause)
object InvalidTokenException : AuthException("Auth002", 400, "유효하지 않는 토큰 입니다.")
object ExpiredTokenException : AuthException("Auth003", 400, "만료된 토큰 입니다.")
class UnAuthenticationException(cause: Throwable? = null) : AuthException("Auth004", 401, "로그인이 필요합니다.", cause)
class UnAuthorizationException(cause: Throwable? = null) : AuthException("Auth004", 403, "권한이 없습니다.", cause)
