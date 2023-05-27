package com.yapp.bol.auth

interface TokenQueryRepository {
    fun findRefreshToken(token: String): Token?
}
