package yapp.rating.auth.token

import java.time.LocalDateTime
import yapp.rating.InvalidTokenException
import yapp.rating.auth.Token

internal interface TokenUtils {
    fun generate(userId: Long, expireAt: LocalDateTime): Token
    fun validate(token: String): Boolean
    fun assertValidate(token: String) {
        if (validate(token).not()) throw InvalidTokenException
    }

    fun getUserId(token: String): Long?
}
