package yapp.rating.auth.dto

import yapp.rating.auth.LoginType

data class LoginRequest(
    val type: LoginType,
    val token: String,
)
