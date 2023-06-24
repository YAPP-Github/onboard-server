package com.yapp.bol.group.member

import com.yapp.bol.auth.UserId

abstract class ParticipantMember internal constructor(
    id: MemberId,
    userId: UserId?,
    nickname: String,
    level: Int,
) : Member(
    id,
    userId,
    nickname,
    level = level,
)
