package com.yapp.bol.group.member

interface MemberService {
    fun validateMemberNickname(groupId: Long, nickname: String): Boolean
}
