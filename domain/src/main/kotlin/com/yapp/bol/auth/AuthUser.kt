package com.yapp.bol.auth

@JvmInline
value class UserId(val value: Long)

data class AuthUser(
    val id: UserId,
)
