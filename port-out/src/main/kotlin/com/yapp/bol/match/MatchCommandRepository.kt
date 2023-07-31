package com.yapp.bol.match

import com.yapp.bol.game.member.GameMember

interface MatchCommandRepository {
    fun createMatch(match: Match, gameMembers: List<GameMember>): Match
}
