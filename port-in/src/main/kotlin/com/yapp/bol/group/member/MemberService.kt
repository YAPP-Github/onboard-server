package com.yapp.bol.group.member

import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.dto.CreateMemberDto

interface MemberService {
    fun validateMemberNickname(groupId: GroupId, nickname: String): Boolean

    fun createMembers(createMemberDtos: List<CreateMemberDto>): MemberList
}
