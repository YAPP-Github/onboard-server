package com.yapp.bol.group.dto

import com.yapp.bol.group.Group
import com.yapp.bol.group.GroupBasicInfo
import com.yapp.bol.group.member.Member
import com.yapp.bol.group.member.MemberList

data class GroupWithMemberCount(
    val group: GroupBasicInfo,
    val memberCount: Int
) : GroupBasicInfo by group {
    companion object {
        fun of(group: Group, members: MemberList) = GroupWithMemberCount(
            group = group,
            memberCount = members.getSize()
        )

        fun of(group: Group, members: List<Member>) = GroupWithMemberCount(
            group = group,
            memberCount = members.size
        )
    }
}
