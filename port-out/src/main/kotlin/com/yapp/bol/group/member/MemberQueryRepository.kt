package com.yapp.bol.group.member

interface MemberQueryRepository {
    fun findByNicknameAndGroupId(nickname: String, groupId: Long): Member?

    fun findByGroupId(groupId: Long): List<Member>
}
