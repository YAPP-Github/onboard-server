package yapp.rating.auth.token

import org.springframework.stereotype.Repository
import yapp.rating.auth.Token

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

    private fun Token.toAccessToken(): AccessTokenEntity =
        AccessTokenEntity(
            userId = this.userId,
            accessToken = this.toBinary(),
            expireAt = this.expireAt,
        )

    private fun Token.toRefreshToken(): RefreshTokenEntity =
        RefreshTokenEntity(
            userId = this.userId,
            refreshToken = this.toBinary(),
            expireAt = this.expireAt,
        )
}
