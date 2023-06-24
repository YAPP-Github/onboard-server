package com.yapp.bol.group.dto

import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId

data class AddGuestDto(
    val groupId: GroupId,
    val requestUserId: UserId,
    val nickname: String,
)
