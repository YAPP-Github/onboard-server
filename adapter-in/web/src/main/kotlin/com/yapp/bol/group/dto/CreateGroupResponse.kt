package com.yapp.bol.group.dto

import com.yapp.bol.group.Group

data class CreateGroupResponse(
    val id: Long,
    val accessCode: String
)

fun Group.toDto() = CreateGroupResponse(id, accessCode)
