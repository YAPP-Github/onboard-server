package com.yapp.bol.group.member

import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId

interface MemberService {
    fun validateMemberNickname(groupId: GroupId, nickname: String): Boolean
    fun createMember(userId: UserId?, groupId: GroupId, nickname: String?, isOwner: Boolean = false): Member
}
