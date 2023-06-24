package com.yapp.bol.group.member.dto

import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.MemberRole

data class CreateMemberDto(
    val userId: UserId? = null,
    val role: MemberRole,
    val nickname: String,
    val groupId: GroupId,
)
