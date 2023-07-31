package com.yapp.bol.group.member

class GuestMember(
    id: MemberId = MemberId(0),
    nickname: String,
    level: Int = 0,
) : ParticipantMember(
    id = id,
    nickname = nickname,
    userId = null,
    level = level,
)
