package yapp.rating.auth

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
internal class AuthQueryRepositoryImpl(
    private val authSocialRepository: AuthSocialRepository,
) : AuthQueryRepository {
    override fun findAuthUser(id: Long): AuthUser? {
        val authSocial = authSocialRepository.findByUserId(id) ?: return null

        return AuthUser(authSocial.userId)
    }

    override fun findAuthUser(socialType: LoginType, socialId: String): AuthUser? {
        val authSocial =
            authSocialRepository.findByIdOrNull(SocialInfo(socialType.toSocialType(), socialId)) ?: return null

        return AuthUser(authSocial.userId)
    }
}
