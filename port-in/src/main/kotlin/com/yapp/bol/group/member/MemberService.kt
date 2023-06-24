package com.yapp.bol.group.member

import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId

interface MemberService {
    fun validateMemberNickname(groupId: GroupId, nickname: String): Boolean

    fun createHostMember(userId: UserId, groupId: GroupId, nickname: String): HostMember

    fun findMembersByGroupId(groupId: GroupId): List<Member>
}
