package com.yapp.bol.group.dto

import com.yapp.bol.group.GroupId
import com.yapp.bol.pagination.CursorRequest

data class GetMembersByCursorDto(
    val groupId: GroupId,
    override val size: Int,
    override val cursor: String?
) : CursorRequest<String>
