package com.yapp.bol.auth.token

import com.yapp.bol.auth.Token
import com.yapp.bol.auth.UserId
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

    override fun deleteAllToken(userId: UserId) {
        accessTokenRepository.deleteByUserId(userId.value)
        refreshTokenRepository.deleteByUserId(userId.value)
    }

    private fun Token.toAccessToken(): AccessTokenEntity =
        AccessTokenEntity(
            userId = this.userId.value,
            accessToken = this.toBinary(),
            expiredAt = this.expiredAt,
        )

    private fun Token.toRefreshToken(): RefreshTokenEntity =
        RefreshTokenEntity(
            userId = this.userId.value,
            refreshToken = this.toBinary(),
            expiredAt = this.expiredAt,
        )
}
