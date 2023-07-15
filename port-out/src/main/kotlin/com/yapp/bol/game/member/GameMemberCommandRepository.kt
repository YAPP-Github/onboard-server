package com.yapp.bol.game.member

import com.yapp.bol.group.GroupId

interface GameMemberCommandRepository {
    fun createGameMember(gameMember: GameMember, groupId: GroupId): GameMember
}
