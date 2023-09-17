package com.yapp.bol.group.member

import com.yapp.bol.group.member.dto.PaginationCursorMemberRequest

interface CustomMemberRepository {

    fun getByGroupIdWithCursor(request: PaginationCursorMemberRequest): List<MemberEntity>
}
