package com.yapp.bol.auth

import com.yapp.bol.auth.social.SocialLoginClient
import org.springframework.stereotype.Service

@Service
internal class AuthServiceImpl(
    private val socialAuthClients: List<SocialLoginClient>,
    private val authCommandRepository: AuthCommandRepository,
    private val authQueryRepository: AuthQueryRepository,
    private val tokenService: TokenService,
) : AuthService {
    override fun login(loginType: LoginType, token: String): AuthToken {
        val socialAuthClient =
            socialAuthClients.find { client -> client.isSupport(loginType) } ?: throw UnsupportedOperationException()
        val socialUser = socialAuthClient.login(token)

        val authUser = getOrCreateAuthUser(loginType, socialUser.id)

        return AuthToken(
            accessToken = tokenService.generateAccessToken(authUser.id),
            refreshToken = tokenService.generateRefreshToken(authUser.id),
        )
    }

    private fun getOrCreateAuthUser(loginType: LoginType, socialUserId: String): AuthUser {
        val authUser = authQueryRepository.findAuthUser(loginType, socialUserId)
        if (authUser != null) return authUser

        return authCommandRepository.registerUser(loginType, socialUserId)
    }
}
