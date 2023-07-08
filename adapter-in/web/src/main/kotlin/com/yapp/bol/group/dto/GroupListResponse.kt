package com.yapp.bol.group.dto

import com.yapp.bol.group.GroupId

data class GroupListResponse(
    val id: GroupId,
    val name: String,
    val description: String,
    val organization: String?,
    val profileImageUrl: String,
    val memberCount: Int,
)

fun GroupWithMemberCount.toListResponse(): GroupListResponse =
    GroupListResponse(
        id = this.id,
        name = this.name,
        description = this.description,
        organization = this.organization,
        profileImageUrl = this.profileImageUrl,
        memberCount = this.memberCount,
    )
