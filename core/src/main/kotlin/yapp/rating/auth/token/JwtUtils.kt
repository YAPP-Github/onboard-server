package yapp.rating.auth.token

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.security.Key
import java.time.LocalDateTime
import yapp.rating.auth.Token
import yapp.rating.toDate

internal class JwtUtils(
    private val secretKey: ByteArray,
) : TokenUtils {
    override fun generate(userId: Long, expiredAt: LocalDateTime): Token {
        val token = Jwts.builder()
            .setId(userId.toString())
            .setExpiration(expiredAt.toDate())
            .signWith(getSigningKey())
            .compact()

        return Token(token, userId, expiredAt)
    }

    override fun validate(token: String): Boolean =
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
            true
        } catch (e: JwtException) {
            false
        }

    override fun getUserId(token: String): Long =
        Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token).body.id.toLong()

    private fun getSigningKey(): Key = Keys.hmacShaKeyFor(secretKey)
}
