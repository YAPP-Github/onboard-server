package com.yapp.bol.group

import com.yapp.bol.group.member.MemberList

data class GroupMemberList(
    val group: Group,
    val members: MemberList,
)
