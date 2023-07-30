package com.yapp.bol.season

import com.yapp.bol.group.GroupId

interface SeasonService {
    fun getOrCreateSeason(groupId: GroupId): Season
}
