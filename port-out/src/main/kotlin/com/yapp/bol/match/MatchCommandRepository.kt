package com.yapp.bol.match

import com.yapp.bol.game.member.GameMember
import com.yapp.bol.match.dto.MatchWithMatchMemberList

interface MatchCommandRepository {
    fun createMatch(matchWithMatchMembers: MatchWithMatchMemberList, gameMembers: List<GameMember>): MatchWithMatchMemberList
}
