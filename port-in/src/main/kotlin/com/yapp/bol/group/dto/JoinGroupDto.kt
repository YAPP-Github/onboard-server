package com.yapp.bol.group.dto

import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.MemberId

data class JoinGroupDto(
    val groupId: GroupId,
    val userId: UserId,
    val nickname: String,
    val accessCode: String,
    val guestId: MemberId?,
)
