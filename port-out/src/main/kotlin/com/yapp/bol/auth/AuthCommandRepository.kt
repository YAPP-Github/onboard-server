package com.yapp.bol.auth

interface AuthCommandRepository {
    fun registerUser(loginType: LoginType, socialId: String): AuthUser
}
