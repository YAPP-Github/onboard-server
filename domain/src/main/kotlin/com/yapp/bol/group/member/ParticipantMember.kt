package com.yapp.bol.group.member

import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId

abstract class ParticipantMember internal constructor(
    id: MemberId,
    userId: UserId?,
    nickname: String,
    groupId: GroupId,
) : Member(
    id,
    userId,
    nickname,
    groupId,
)
