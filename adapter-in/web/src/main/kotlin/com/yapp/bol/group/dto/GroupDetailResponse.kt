package com.yapp.bol.group.dto

import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.OwnerMember
import com.yapp.bol.group.member.dto.MemberResponse
import com.yapp.bol.group.member.dto.toResponse

data class GroupDetailResponse(
    val id: GroupId,
    val name: String,
    val description: String,
    val organization: String?,
    val profileImageUrl: String,
    val accessCode: String,
    val memberCount: Int,
    val owner: MemberResponse,
) {
    companion object {
        fun of(group: GroupWithMemberCount, owner: OwnerMember): GroupDetailResponse =
            GroupDetailResponse(
                id = group.id,
                name = group.name,
                description = group.description,
                organization = group.organization,
                profileImageUrl = group.profileImageUrl,
                accessCode = group.accessCode,
                memberCount = group.memberCount,
                owner = owner.toResponse(),
            )
    }
}
