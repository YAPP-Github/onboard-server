package com.yapp.bol.group.member

import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.dto.GetMembersByCursorDto
import com.yapp.bol.pagination.SimpleCursorResponse

interface MemberService {
    fun validateMemberNickname(groupId: GroupId, nickname: String): Boolean

    fun createHostMember(userId: UserId, groupId: GroupId, nickname: String): HostMember

    fun createGuestMember(groupId: GroupId, nickname: String): GuestMember

    fun getMembers(request: GetMembersByCursorDto): SimpleCursorResponse<Member, String>
}
