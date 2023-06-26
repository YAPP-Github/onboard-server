package com.yapp.bol.group.member.dto

import com.yapp.bol.group.GroupId
import com.yapp.bol.pagination.cursor.PaginationCursorRequest

data class PaginationCursorMemberRequest(
    val groupId: GroupId,
    val nickname: String?,
    override val size: Int,
    override val cursor: String?,
) : PaginationCursorRequest<String>
