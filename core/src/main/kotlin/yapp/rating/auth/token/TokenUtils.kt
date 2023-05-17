package yapp.rating.auth.token

import java.time.LocalDateTime
import yapp.rating.InvalidTokenException
import yapp.rating.auth.Token

internal interface TokenUtils {
    fun generate(userId: Long, expiredAt: LocalDateTime): Token
    fun validate(token: String): Boolean
    fun getUserId(token: String): Long?
    fun assertValidate(token: String) {
        if (validate(token).not()) throw InvalidTokenException
    }
}
