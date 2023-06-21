package com.yapp.bol.group.member

import com.yapp.bol.auth.UserId

abstract class ParticipantMember internal constructor(
    id: MemberId,
    userId: UserId?,
    nickname: String,
) : Member(
    id,
    userId,
    nickname,
)
