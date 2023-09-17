package com.yapp.bol.group.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.OwnerMember
import com.yapp.bol.group.member.dto.MemberResponse
import com.yapp.bol.group.member.dto.toResponse

@JsonInclude(JsonInclude.Include.NON_NULL)
data class GroupDetailResponse(
    val id: GroupId,
    val name: String,
    val description: String,
    val organization: String?,
    val profileImageUrl: String,
    val accessCode: String,
    val memberCount: Int,
    val owner: MemberResponse,
    val isRegister: Boolean?,
) {
    companion object {
        fun of(group: GroupWithMemberCount, owner: OwnerMember, isRegister: Boolean?): GroupDetailResponse =
            GroupDetailResponse(
                id = group.id,
                name = group.name,
                description = group.description,
                organization = group.organization,
                profileImageUrl = group.profileImageUrl,
                accessCode = group.accessCode,
                memberCount = group.memberCount,
                owner = owner.toResponse(),
                isRegister = isRegister,
            )
    }
}
