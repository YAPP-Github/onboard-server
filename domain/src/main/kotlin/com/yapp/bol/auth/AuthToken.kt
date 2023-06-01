package com.yapp.bol.auth

data class AuthToken(
    val accessToken: Token,
    val refreshToken: Token? = null,
)
