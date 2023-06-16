package com.yapp.bol.group

import com.yapp.bol.group.member.OwnerMember

interface GroupCommandRepository {
    fun createGroup(group: Group, owner: OwnerMember): GroupMemberList
}
