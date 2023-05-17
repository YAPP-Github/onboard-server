package yapp.rating.auth

interface AuthQueryRepository {
    fun findAuthUser(id: Long): AuthUser?
    fun findAuthUser(socialType: LoginType, socialId: String): AuthUser?
}
