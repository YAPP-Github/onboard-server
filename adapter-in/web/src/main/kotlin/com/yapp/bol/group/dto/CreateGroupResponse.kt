package com.yapp.bol.group.dto

import com.yapp.bol.group.GroupId
import com.yapp.bol.group.GroupMemberList

data class CreateGroupResponse(
    val id: GroupId,
    val name: String,
    val description: String,
    val owner: String,
    val organization: String,
    val profileImageUrl: String,
    val accessCode: String,
)

fun GroupMemberList.toCreateGroupResponse() = CreateGroupResponse(
    id = group.id,
    name = group.name,
    description = group.description,
    owner = members.owner.nickname,
    organization = group.organization,
    profileImageUrl = group.profileImageUrl,
    accessCode = group.accessCode,
)
