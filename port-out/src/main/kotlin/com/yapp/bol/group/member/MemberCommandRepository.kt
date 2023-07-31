package com.yapp.bol.group.member

import com.yapp.bol.group.GroupId

interface MemberCommandRepository {
    fun createMember(groupId: GroupId, member: Member): Member
}
