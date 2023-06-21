package com.yapp.bol.user

import com.yapp.bol.auth.UserId

data class User(
    val id: UserId,
    val nickname: String,
)
