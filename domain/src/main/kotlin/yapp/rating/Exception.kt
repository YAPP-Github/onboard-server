package yapp.rating

import java.lang.RuntimeException

sealed class BolRatingException(
    override val message: String,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)

sealed class LoginException(msg: String, cause: Throwable? = null) : BolRatingException(msg, cause)
class SocialLoginException(cause: Throwable? = null) : LoginException("로그인이 실패했습니다.", cause)
object InvalidTokenException : LoginException("유효하지 않는 토큰 입니다.")
