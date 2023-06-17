package com.yapp.bol.group.member

import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId

interface MemberQueryRepository {
    fun findByNicknameAndGroupId(nickname: String, groupId: GroupId): Member?

    fun findByGroupId(groupId: GroupId): List<Member>

    fun findByGroupIdAndUserId(groupId: GroupId, userId: UserId): Member?
}
