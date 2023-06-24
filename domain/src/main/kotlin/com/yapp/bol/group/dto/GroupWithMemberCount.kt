package com.yapp.bol.group.dto

import com.yapp.bol.group.Group
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.Member
import com.yapp.bol.group.member.MemberList

data class GroupWithMemberCount(
    val id: GroupId,
    val name: String,
    val description: String,
    val organization: String?,
    val profileImageUrl: String,
    val memberCount: Int
) {
    companion object {
        fun of(group: Group, members: MemberList) = GroupWithMemberCount(
            id = group.id,
            name = group.name,
            description = group.description,
            organization = group.organization,
            profileImageUrl = group.profileImageUrl,
            memberCount = members.getSize()
        )

        fun of(group: Group, members: List<Member>) = GroupWithMemberCount(
            id = group.id,
            name = group.name,
            description = group.description,
            organization = group.organization,
            profileImageUrl = group.profileImageUrl,
            memberCount = members.size
        )
    }
}
