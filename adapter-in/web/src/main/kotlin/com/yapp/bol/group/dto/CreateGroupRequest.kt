package com.yapp.bol.group.dto

data class CreateGroupRequest(
    val name: String,
    val description: String,
    val organization: String,
    val profileImageUrl: String?,
    val ownerId: Long,
    val nickname: String,
)

fun CreateGroupRequest.toDto() = CreateGroupDto(
    name = name,
    description = description,
    organization = organization,
    profileImageUrl = profileImageUrl,
    ownerId = ownerId,
    nickname = nickname,
)
