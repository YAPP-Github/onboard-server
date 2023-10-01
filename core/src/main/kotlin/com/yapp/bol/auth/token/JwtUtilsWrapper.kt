package com.yapp.bol.auth.token

import com.yapp.bol.auth.Token
import com.yapp.bol.auth.UserId
import com.yapp.bol.jwt.JwtUtils
import com.yapp.bol.jwt.key.JwtKey
import java.time.LocalDateTime

internal class JwtUtilsWrapper(
    secretKey: ByteArray,
) : TokenUtils {
    private val jwtKey = JwtKey(secretKey)

    override fun generate(userId: UserId, expiredAt: LocalDateTime): Token {
        val token = JwtUtils.generate(userId.toString(), expiredAt, jwtKey)
        return Token(token, userId, expiredAt)
    }

    override fun validate(token: String): Boolean =
        JwtUtils.validate(token, jwtKey)

    override fun getUserId(token: String): Long =
        JwtUtils.getUserId(token, jwtKey).toLong()
}
