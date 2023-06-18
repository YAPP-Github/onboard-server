package com.yapp.bol.group.member

import com.yapp.bol.group.GroupId

class GuestMember(
    id: MemberId = MemberId(0),
    nickname: String,
    groupId: GroupId,
) : ParticipantMember(
    id = id,
    nickname = nickname,
    groupId = groupId,
    userId = null,
)
