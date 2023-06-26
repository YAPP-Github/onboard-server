package com.yapp.bol.group.member

import com.yapp.bol.pagination.cursor.PaginationCursorRequest

interface CustomMemberRepository {

    fun getByGroupIdWithCursor(groupId: Long, nickname: String?, cursor: PaginationCursorRequest<String>): List<MemberEntity>
}
