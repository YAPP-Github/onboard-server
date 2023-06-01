package com.yapp.bol.auth

import com.yapp.bol.auth.token.TokenCommandRepository
import com.yapp.bol.auth.token.TokenPolicy
import org.springframework.beans.factory.annotation.Qualifier

internal class TokenService(
    @Qualifier("accessTokenPolicy") private val accessTokenPolicy: TokenPolicy,
    @Qualifier("refreshTokenPolicy") private val refreshTokenPolicy: TokenPolicy,
    private val tokenQueryRepository: TokenQueryRepository,
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

    fun renewRefreshToken(token: Token): Token {
        token.validate()
        tokenCommandRepository.removeRefreshToken(token)
        return generateRefreshToken(token.userId)
    }

    fun validateRefreshToken(value: String): Token {
        refreshTokenPolicy.assertValidate(value)
        return tokenQueryRepository.findRefreshToken(value).validate()
    }
}
