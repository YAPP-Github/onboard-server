package com.yapp.bol.game

import com.yapp.bol.group.GroupId

interface GameQueryRepository {
    fun getGameListByGroupId(groupId: GroupId): List<GameWithMatchCount>

    fun findById(id: GameId): Game?
}
