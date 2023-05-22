package yapp.rating.auth.dto

import com.fasterxml.jackson.annotation.JsonInclude
import yapp.rating.auth.AuthToken

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
