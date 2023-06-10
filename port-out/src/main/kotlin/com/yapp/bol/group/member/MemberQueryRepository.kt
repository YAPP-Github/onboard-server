package com.yapp.bol.group.member

interface MemberQueryRepository {
    fun findByNickname(nickname: String): Member?
}
