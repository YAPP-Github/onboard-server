package com.yapp.bol.group.member

import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.dto.PaginationCursorMemberRequest
import com.yapp.bol.pagination.cursor.SimplePaginationCursorResponse

interface MemberQueryRepository {
    fun findByNicknameAndGroupId(nickname: String, groupId: GroupId): Member?
    fun findByGroupId(groupId: GroupId): List<Member>
    fun findByGroupIdAndUserId(groupId: GroupId, userId: UserId): Member?

    fun getMemberListByCursor(request: PaginationCursorMemberRequest): SimplePaginationCursorResponse<Member, String>
    fun findOwner(groupId: GroupId): OwnerMember
}
