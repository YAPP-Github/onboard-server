package com.yapp.bol.user

import com.yapp.bol.auth.UserId

interface UserCommandRepository {
    fun updateUser(user: User)
    fun delete(userId: UserId)
}
