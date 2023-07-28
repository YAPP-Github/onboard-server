package com.yapp.bol.auth.token

import com.yapp.bol.auth.Token
import com.yapp.bol.auth.UserId

interface TokenCommandRepository {
    fun saveAccessToken(token: Token)
    fun saveRefreshToken(token: Token)

    fun removeRefreshToken(token: Token)

    fun deleteAllToken(userId: UserId)
}
