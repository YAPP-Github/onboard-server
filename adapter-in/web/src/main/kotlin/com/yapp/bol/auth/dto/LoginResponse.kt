package com.yapp.bol.auth.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.yapp.bol.auth.AuthToken

@JsonInclude(JsonInclude.Include.NON_NULL)
data class LoginResponse(
    val accessToken: String,
    val refreshToken: String?,
)

fun AuthToken.toResponse(): LoginResponse =
    LoginResponse(
        accessToken = this.accessToken.value,
        refreshToken = this.refreshToken?.value,
    )
