package com.yapp.bol.game.member

import com.yapp.bol.game.GameId
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.MemberId

interface GameMemberService {
    fun getOrCraeteGameMembers(gameId: GameId, groupId: GroupId, memberIds: List<MemberId>): List<GameMember>
}
