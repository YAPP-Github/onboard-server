package com.yapp.bol.group.member.dto

import com.yapp.bol.group.member.MemberRole

data class CreateMemberDto(
    val userId: Long? = null,
    val role: MemberRole,
    val nickname: String,
    val groupId: Long,
)
