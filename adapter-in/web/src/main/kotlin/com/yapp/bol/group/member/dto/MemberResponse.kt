package com.yapp.bol.group.member.dto

import com.yapp.bol.group.member.Member
import com.yapp.bol.group.member.MemberId
import com.yapp.bol.group.member.MemberRole

data class MemberResponse(
    val id: MemberId?,
    val role: MemberRole,
    val nickname: String,
    val level: Int,
)

fun Member.toResponse(): MemberResponse = MemberResponse(
    id = this.id,
    nickname = this.nickname,
    role = this.role,
    level = this.level,
)
