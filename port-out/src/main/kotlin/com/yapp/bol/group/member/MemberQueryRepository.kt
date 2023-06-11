package com.yapp.bol.group.member

interface MemberQueryRepository {
    fun findByNicknameAndGroupId(groupId: Long, nickname: String): Member?

    fun findByGroupId(groupId: Long): List<Member>
}
