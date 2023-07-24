package com.yapp.bol.game.member

import com.yapp.bol.game.GameId
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.MemberId

interface GameMemberQueryRepository {
    fun findGameMember(memberId: MemberId, gameId: GameId, groupId: GroupId): GameMember?
}
