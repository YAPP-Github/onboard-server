package com.yapp.bol.auth.token

import com.yapp.bol.auth.Token
import org.springframework.stereotype.Repository

@Repository
internal class TokenCommandRepositoryImpl(
    private val accessTokenRepository: AccessTokenRepository,
    private val refreshTokenRepository: RefreshTokenRepository,
) : TokenCommandRepository {
    override fun saveAccessToken(token: Token) {
        accessTokenRepository.save(token.toAccessToken())
    }

    override fun saveRefreshToken(token: Token) {
        refreshTokenRepository.save(token.toRefreshToken())
    }

    override fun removeRefreshToken(token: Token) {
        refreshTokenRepository.deleteByRefreshToken(token.toBinary())
    }

    private fun Token.toAccessToken(): AccessTokenEntity =
        AccessTokenEntity(
            userId = this.userId,
            accessToken = this.toBinary(),
            expiredAt = this.expiredAt,
        )

    private fun Token.toRefreshToken(): RefreshTokenEntity =
        RefreshTokenEntity(
            userId = this.userId,
            refreshToken = this.toBinary(),
            expiredAt = this.expiredAt,
        )
}
