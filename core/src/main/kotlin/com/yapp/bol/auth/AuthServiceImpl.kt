package com.yapp.bol.auth

import com.yapp.bol.AuthException
import com.yapp.bol.auth.social.SocialLoginClient
import org.springframework.stereotype.Service

@Service
internal class AuthServiceImpl(
    private val socialAuthClient: SocialLoginClient,
    private val authCommandRepository: AuthCommandRepository,
    private val authQueryRepository: AuthQueryRepository,
    private val tokenService: TokenService,
) : AuthService {
    override fun login(loginType: LoginType, token: String): AuthToken {
        return if (loginType == LoginType.REFRESH) loginByRefreshToken(token)
        else socialLogin(loginType, token)
    }

    override fun getAuthUserByAccessToken(token: String): AuthUser? {
        return try {
            val accessToken = tokenService.validateAccessToken(token)
            AuthUser(accessToken.userId)
        } catch (e: AuthException) {
            null
        }
    }

    private fun socialLogin(loginType: LoginType, token: String): AuthToken {
        val socialUser = socialAuthClient.login(loginType, token)

        val authUser = getOrCreateAuthUser(loginType, socialUser.id)

        return AuthToken(
            accessToken = tokenService.generateAccessToken(authUser.id),
            refreshToken = tokenService.generateRefreshToken(authUser.id),
        )
    }

    private fun loginByRefreshToken(tokenValue: String): AuthToken {
        val token = tokenService.validateRefreshToken(tokenValue)

        return AuthToken(
            accessToken = tokenService.generateAccessToken(token.userId),
            refreshToken = tokenService.renewRefreshToken(token),
        )
    }

    private fun getOrCreateAuthUser(loginType: LoginType, socialUserId: String): AuthUser {
        val authUser = authQueryRepository.findAuthUser(loginType, socialUserId)
        if (authUser != null) return authUser

        return authCommandRepository.registerUser(loginType, socialUserId)
    }
}
