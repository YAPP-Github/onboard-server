package com.yapp.bol.group.member

import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId

interface MemberCommandRepository {
    fun createMember(groupId: GroupId, member: Member): Member

    fun unregisterUser(userId: UserId)
}
