package com.yapp.bol.season

import com.yapp.bol.group.GroupId

@JvmInline
value class SeasonId(val value: Long)

data class Season(
    val id: SeasonId = SeasonId(0),
    val groupId: GroupId,
)
