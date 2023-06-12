package com.yapp.bol.group.dto

data class CreateGroupRequest(
    val name: String,
    val description: String,
    val organization: String,
    val profileImageUrl: String?,
    val nickname: String?,
)

fun CreateGroupRequest.toDto(ownerId: Long) = CreateGroupDto(
    name = name,
    description = description,
    organization = organization,
    profileImageUrl = profileImageUrl,
    ownerId = ownerId,
    nickname = nickname ?: "닉네임", // FIXME: 시큐리티 적용
)
