package com.yapp.bol.group.dto

import com.yapp.bol.group.Group
import com.yapp.bol.group.member.MemberList

data class GroupMemberList(
    val group: Group,
    val members: MemberList,
)
