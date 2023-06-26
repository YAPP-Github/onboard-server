package com.yapp.bol.pagination.cursor.member

import com.yapp.bol.group.GroupId
import com.yapp.bol.pagination.cursor.CursorRequest

data class MemberCursorRequest(
    val groupId: GroupId,
    val nickname: String?,
    override val size: Int,
    override val cursor: String?,
) : CursorRequest<String>
