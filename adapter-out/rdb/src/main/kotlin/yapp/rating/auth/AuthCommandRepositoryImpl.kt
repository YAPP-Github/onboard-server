package yapp.rating.auth

import org.springframework.stereotype.Repository
import yapp.rating.user.UserEntity
import yapp.rating.user.UserRepository

@Repository
internal class AuthCommandRepositoryImpl(
    private val authSocialRepository: AuthSocialRepository,
    private val userRepository: UserRepository,
) : AuthCommandRepository {
    override fun registerUser(loginType: LoginType, socialId: String): AuthUser {
        val user = userRepository.save(UserEntity())
        authSocialRepository.save(AuthSocialEntity(loginType.toSocialType(), socialId, user.id))

        return AuthUser(user.id)
    }
}
