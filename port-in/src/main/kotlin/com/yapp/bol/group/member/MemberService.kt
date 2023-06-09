package com.yapp.bol.group.member

interface MemberService {
    fun validateMemberNickname(nickname: String): Boolean
}
