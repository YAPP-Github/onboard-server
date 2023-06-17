package com.yapp.bol.game

interface GameService {
    fun getGameList(groupId: Long): List<Game>
}
