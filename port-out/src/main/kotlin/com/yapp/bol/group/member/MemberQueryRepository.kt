package com.yapp.bol.group.member

interface MemberQueryRepository {
    fun findByNickname(groupId: Long, nickname: String): Member?
}
