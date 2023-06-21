package com.yapp.bol.user

import com.yapp.bol.auth.UserId

interface UserQueryRepository {
    fun getUser(userId: UserId): User?
}
