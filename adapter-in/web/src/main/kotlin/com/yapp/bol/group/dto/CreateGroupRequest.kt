package com.yapp.bol.group.dto

import com.yapp.bol.auth.UserId

data class CreateGroupRequest(
    val name: String,
    val description: String,
    val organization: String?,
    val profileImageUrl: String?,
    val nickname: String?,
)

fun CreateGroupRequest.toDto(ownerId: UserId) = CreateGroupDto(
    name = name,
    description = description,
    organization = organization,
    profileImageUrl = profileImageUrl,
    ownerId = ownerId,
    nickname = nickname
)
