package com.yapp.bol.group.dto

data class CreateGroupDto(
    val name: String,
    val description: String,
    val organization: String?,
    val profileImageUrl: String?,
    val ownerId: Long,
    val nickname: String,
)
