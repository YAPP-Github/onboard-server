package com.yapp.bol.group.member

import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId
import com.yapp.bol.pagination.CursorRequest

interface MemberQueryRepository {
    fun findByNicknameAndGroupId(nickname: String, groupId: GroupId): Member?

    fun findByGroupId(groupId: GroupId): List<Member>

    fun findByGroupIdAndUserId(groupId: GroupId, userId: UserId): Member?

    fun getMemberListByCursor(groupId: GroupId, nickname: String?, cursorRequest: CursorRequest<String>): List<Member>
}
