package com.yapp.bol.auth.token

import com.yapp.bol.InvalidTokenException
import com.yapp.bol.auth.Token
import java.time.LocalDateTime

internal interface TokenUtils {
    fun generate(userId: Long, expiredAt: LocalDateTime): Token
    fun validate(token: String): Boolean
    fun getUserId(token: String): Long?
    fun assertValidate(token: String) {
        if (validate(token).not()) throw InvalidTokenException
    }
}
