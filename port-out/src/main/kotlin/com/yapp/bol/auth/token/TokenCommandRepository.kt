package com.yapp.bol.auth.token

import com.yapp.bol.auth.Token

interface TokenCommandRepository {
    fun saveAccessToken(token: Token)
    fun saveRefreshToken(token: Token)

    fun removeRefreshToken(token: Token)
}
