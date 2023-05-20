package com.yapp.bol.auth

import com.yapp.bol.auth.token.TokenCommandRepository
import com.yapp.bol.auth.token.TokenPolicy
import org.springframework.beans.factory.annotation.Qualifier

internal class TokenService(
    @Qualifier("accessTokenPolicy") private val accessTokenPolicy: TokenPolicy,
    @Qualifier("refreshTokenPolicy") private val refreshTokenPolicy: TokenPolicy,
    private val tokenCommandRepository: TokenCommandRepository,
) {
    fun generateAccessToken(userId: Long): Token {
        val token = accessTokenPolicy.generate(userId)
        tokenCommandRepository.saveAccessToken(token)
        return token
    }

    fun generateRefreshToken(userId: Long): Token {
        val token = refreshTokenPolicy.generate(userId)
        tokenCommandRepository.saveRefreshToken(token)
        return token
    }
}