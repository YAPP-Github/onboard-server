package com.yapp.bol.group.dto

import com.yapp.bol.auth.UserId

data class CreateGroupDto(
    val name: String,
    val description: String,
    val organization: String?,
    val profileImageUrl: String?,
    val ownerId: UserId,
    val nickname: String?,
)
