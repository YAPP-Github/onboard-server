package yapp.rating.auth

data class AuthToken(
    val accessToken: Token,
    val refreshToken: Token? = null,
)
