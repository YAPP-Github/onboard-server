package yapp.rating.auth.token

import yapp.rating.auth.Token

interface TokenCommandRepository {
    fun saveAccessToken(token: Token)
    fun saveRefreshToken(token: Token)
}
