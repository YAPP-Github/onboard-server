package com.yapp.bol.auth.token

import com.yapp.bol.auth.Token
import com.yapp.bol.auth.TokenQueryRepository
import com.yapp.bol.auth.toBinary
import org.springframework.stereotype.Repository

@Repository
internal class TokenQueryRepositoryImpl(
    private val accessTokenRepository: AccessTokenRepository,
    private val refreshTokenRepository: RefreshTokenRepository,
) : TokenQueryRepository {
    override fun findAccessToken(value: String): Token? {
        val entity = accessTokenRepository.findByAccessToken(value.toBinary()) ?: return null
        return entity.toToken()
    }

    override fun findRefreshToken(token: String): Token? {
        val entity = refreshTokenRepository.findByRefreshToken(token.toBinary()) ?: return null
        return entity.toToken()
    }

    private fun AccessTokenEntity.toToken(): Token {
        return Token(
            value = this.accessToken,
            userId = this.userId,
            expiredAt = this.expiredAt,
        )
    }

    private fun RefreshTokenEntity.toToken(): Token {
        return Token(
            value = this.refreshToken,
            userId = this.userId,
            expiredAt = this.expiredAt,
        )
    }
}
