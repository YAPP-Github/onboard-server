package com.yapp.bol.game

import com.yapp.bol.group.GroupId

interface GameService {
    fun getGameList(groupId: GroupId): List<Game>
}
