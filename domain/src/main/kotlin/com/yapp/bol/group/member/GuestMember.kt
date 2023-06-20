package com.yapp.bol.group.member

class GuestMember(
    id: MemberId = MemberId(0),
    nickname: String,
) : ParticipantMember(
    id = id,
    nickname = nickname,
    userId = null,
)
