package com.yapp.bol.pagination.group.member

import com.yapp.bol.group.GroupId
import com.yapp.bol.pagination.CursorRequest

data class MemberCursorRequest(
    val groupId: GroupId,
    val nickname: String?,
    override val size: Int,
    override val cursor: String?,
) : CursorRequest<String>
