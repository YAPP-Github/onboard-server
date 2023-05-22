package com.yapp.bol.auth.token

import com.yapp.bol.auth.Token
import java.security.SecureRandom
import java.time.LocalDateTime
import java.util.Base64

internal class OpaqueTokenUtils(
    private val tokenLength: Int,
) : TokenUtils {
    private val tokenByteSize = tokenLength * 6 / 8

    private val random = SecureRandom()

    override fun generate(userId: Long, expiredAt: LocalDateTime): Token {
        val arr = ByteArray(tokenByteSize)
        random.nextBytes(arr)
        val value = Base64.getEncoder().encodeToString(arr)
        return Token(value, userId, expiredAt)
    }

    override fun validate(token: String): Boolean {
        return token.length == tokenLength
    }

    override fun getUserId(token: String): Long? = null
}
