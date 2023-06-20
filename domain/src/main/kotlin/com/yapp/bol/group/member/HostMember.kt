package com.yapp.bol.group.member

import com.yapp.bol.auth.UserId

class HostMember(
    id: MemberId = MemberId(0),
    userId: UserId,
    nickname: String,
) : ParticipantMember(
    id = id,
    userId = userId,
    nickname = nickname,
)
