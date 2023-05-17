package yapp.rating.auth.token

import java.time.Duration
import java.time.LocalDateTime
import yapp.rating.auth.Token

internal class TokenPolicy(
    private val tokenUtils: TokenUtils,
    private val expiredDuration: Duration,
) : TokenUtils by tokenUtils {
    fun generate(userId: Long): Token = tokenUtils.generate(userId, LocalDateTime.now().plus(expiredDuration))
}
