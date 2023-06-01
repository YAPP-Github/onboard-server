package com.yapp.bol.auth.token

import com.yapp.bol.auth.Token
import com.yapp.bol.auth.TokenQueryRepository
import com.yapp.bol.auth.toBinary
import org.springframework.stereotype.Repository

@Repository
internal class TokenQueryRepositoryImpl(
    private val refreshTokenRepository: RefreshTokenRepository,
) : TokenQueryRepository {
    override fun findRefreshToken(token: String): Token? {
        val entity = refreshTokenRepository.findByRefreshToken(token.toBinary()) ?: return null
        return entity.toToken()
    }

    private fun RefreshTokenEntity.toToken(): Token {
        return Token(
            value = this.refreshToken,
            userId = this.userId,
            expiredAt = this.expiredAt,
        )
    }
}
