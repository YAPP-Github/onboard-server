package com.yapp.bol.group.dto

import com.yapp.bol.group.Group

data class CreateGroupResponse(
    val id: Long,
    val name: String,
    val description: String,
    val owner: String,
    val organization: String,
    val profileImageUrl: String,
    val accessCode: String,
)

fun Group.toCreateGroupResponse() = CreateGroupResponse(
    id = this.id,
    name = this.name,
    description = this.description,
    owner = this.members.getOwner().nickname,
    organization = organization,
    profileImageUrl = profileImageUrl,
    accessCode = accessCode,
)
