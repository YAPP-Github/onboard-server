package yapp.rating.auth.token

import java.security.SecureRandom
import java.time.LocalDateTime
import java.util.Base64
import yapp.rating.auth.Token

internal class OpaqueTokenUtils(
    private val tokenLength: Int,
) : TokenUtils {
    private val tokenByteSize = tokenLength * 6 / 8

    private val random = SecureRandom()

    override fun generate(userId: Long, expireAt: LocalDateTime): Token {
        val arr = ByteArray(tokenByteSize)
        random.nextBytes(arr)
        val value = Base64.getEncoder().encodeToString(arr)
        return Token(value, userId, expireAt)
    }

    override fun validate(token: String): Boolean {
        return token.length == tokenLength
    }

    override fun getUserId(token: String): Long? = null
}
