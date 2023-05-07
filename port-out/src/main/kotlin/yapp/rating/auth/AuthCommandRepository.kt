package yapp.rating.auth

interface AuthCommandRepository {
    fun registerUser(loginType: LoginType, socialId: String): AuthUser
}
