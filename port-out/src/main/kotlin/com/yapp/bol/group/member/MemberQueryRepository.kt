package com.yapp.bol.group.member

import com.yapp.bol.group.GroupId

interface MemberQueryRepository {
    fun findByNicknameAndGroupId(nickname: String, groupId: GroupId): Member?

    fun findByGroupId(groupId: GroupId): List<Member>
}
