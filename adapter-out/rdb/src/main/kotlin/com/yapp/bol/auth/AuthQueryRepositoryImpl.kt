package com.yapp.bol.auth

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
internal class AuthQueryRepositoryImpl(
    private val authSocialRepository: AuthSocialRepository,
) : AuthQueryRepository {
    override fun findAuthUser(id: UserId): AuthUser? {
        val authSocial = authSocialRepository.findByUserId(id.value) ?: return null

        return AuthUser(UserId(authSocial.userId))
    }

    override fun findAuthUser(socialType: LoginType, socialId: String): AuthUser? {
        val authSocial =
            authSocialRepository.findByIdOrNull(SocialInfo(socialType.toSocialType(), socialId)) ?: return null

        return AuthUser(UserId(authSocial.userId))
    }
}
