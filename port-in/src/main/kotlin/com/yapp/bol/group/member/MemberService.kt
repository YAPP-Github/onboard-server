package com.yapp.bol.group.member

import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId
import com.yapp.bol.pagination.cursor.SimpleCursorResponse
import com.yapp.bol.pagination.cursor.member.MemberCursorRequest

interface MemberService {
    fun validateMemberNickname(groupId: GroupId, nickname: String): Boolean

    fun createHostMember(userId: UserId, groupId: GroupId, nickname: String): HostMember

    fun createGuestMember(groupId: GroupId, nickname: String): GuestMember

    fun getMembers(request: MemberCursorRequest): SimpleCursorResponse<Member, String>

    fun findMembersByGroupId(groupId: GroupId): List<Member>
}
