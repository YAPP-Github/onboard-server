package com.yapp.bol.auth.dto

import com.yapp.bol.auth.LoginType

data class LoginRequest(
    val type: LoginType,
    val token: String,
)
