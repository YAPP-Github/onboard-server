package com.yapp.bol.group.dto

import com.yapp.bol.group.GroupBasicInfo
import com.yapp.bol.group.GroupId

data class GroupResponse(
    val id: GroupId,
    val name: String,
    val description: String,
    val organization: String?,
    val profileImageUrl: String,
)

fun GroupBasicInfo.toResponse(): GroupResponse =
    GroupResponse(
        id = this.id,
        name = this.name,
        description = this.description,
        organization = this.organization,
        profileImageUrl = this.profileImageUrl,
    )
