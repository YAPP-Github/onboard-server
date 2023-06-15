package com.yapp.bol.group.member

import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId

class HostMember(
    id: MemberId = MemberId(0),
    userId: UserId,
    nickname: String,
    groupId: GroupId,
) : Member(
    id = id,
    userId = userId,
    nickname = nickname,
    groupId = groupId,
)
