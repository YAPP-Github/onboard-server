package com.yapp.bol.group.member

import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.dto.PaginationCursorMemberRequest
import com.yapp.bol.pagination.cursor.SimplePaginationCursorResponse

interface MemberService {
    fun validateMemberNickname(groupId: GroupId, nickname: String): Boolean

    fun createHostMember(userId: UserId, groupId: GroupId, nickname: String): HostMember

    fun createGuestMember(groupId: GroupId, nickname: String): GuestMember

    fun getMembers(request: PaginationCursorMemberRequest): SimplePaginationCursorResponse<Member, String>

    fun findMembersByGroupId(groupId: GroupId): List<Member>

    fun unregister(userId: UserId)
}
