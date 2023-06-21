package com.yapp.bol.user.dto

import com.yapp.bol.auth.UserId
import com.yapp.bol.user.User

data class MyInfoResponse(
    val id: UserId,
    val nickname: String?,
)

fun User.toResponse(): MyInfoResponse = MyInfoResponse(
    id = this.id,
    nickname = this.nickname
)
