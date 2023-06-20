package com.yapp.bol.game

interface GameQueryRepository {
    fun getGameListByGroupId(groupId: Long): List<Game>
}
