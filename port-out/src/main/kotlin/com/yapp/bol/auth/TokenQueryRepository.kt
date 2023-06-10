package com.yapp.bol.auth

interface TokenQueryRepository {
    fun findAccessToken(value: String): Token?
    fun findRefreshToken(token: String): Token?
}
