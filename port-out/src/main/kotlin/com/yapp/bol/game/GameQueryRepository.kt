package com.yapp.bol.game

import com.yapp.bol.group.GroupId

interface GameQueryRepository {
    fun getGameListByGroupId(groupId: GroupId): List<Game>

    fun findById(id: GameId): Game?
}
