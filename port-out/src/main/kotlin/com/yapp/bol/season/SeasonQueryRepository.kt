package com.yapp.bol.season

import com.yapp.bol.group.GroupId

interface SeasonQueryRepository {
    fun getSeason(groupId: GroupId): Season?
}
