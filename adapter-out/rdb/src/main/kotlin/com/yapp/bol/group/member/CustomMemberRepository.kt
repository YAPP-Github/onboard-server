package com.yapp.bol.group.member

import com.yapp.bol.pagination.cursor.CursorRequest

interface CustomMemberRepository {

    fun getByGroupIdWithCursor(groupId: Long, nickname: String?, cursor: CursorRequest<String>): List<MemberEntity>
}
