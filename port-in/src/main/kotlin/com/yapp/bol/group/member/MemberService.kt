package com.yapp.bol.group.member

import com.yapp.bol.group.member.dto.CreateMemberDto

interface MemberService {
    fun validateMemberNickname(groupId: Long, nickname: String): Boolean

    fun createMembers(createMemberDtos: List<CreateMemberDto>): MemberList

    fun findMembersByGroupId(groupId: Long): List<Member>
}
