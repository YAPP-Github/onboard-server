package com.yapp.bol.group.dto

import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId

data class JoinGroupDto(
    val groupId: GroupId,
    val userId: UserId,
    val nickname: String,
    val accessCode: String,
)
