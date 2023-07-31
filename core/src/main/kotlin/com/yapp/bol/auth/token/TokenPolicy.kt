package com.yapp.bol.auth.token

import com.yapp.bol.auth.Token
import com.yapp.bol.auth.UserId
import java.time.Duration
import java.time.LocalDateTime

internal class TokenPolicy(
    private val tokenUtils: TokenUtils,
    private val expiredDuration: Duration,
) : TokenUtils by tokenUtils {
    fun generate(userId: UserId): Token = tokenUtils.generate(userId, LocalDateTime.now().plus(expiredDuration))
}
