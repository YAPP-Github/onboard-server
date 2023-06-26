package com.yapp.bol.user.dto

import com.yapp.bol.group.Group
import com.yapp.bol.group.GroupId

data class JoinedGroupResponse(
    val contents: List<GroupResponse>
)

data class GroupResponse(
    val id: GroupId,
    val name: String,
    val description: String,
    val organization: String?,
    val profileImageUrl: String,
)

fun Group.toResponse(): GroupResponse =
    GroupResponse(
        id = this.id,
        name = this.name,
        description = this.description,
        organization = this.organization,
        profileImageUrl = this.profileImageUrl,
    )
