package com.yapp.bol.match

import com.yapp.bol.game.GameId
import com.yapp.bol.group.GroupId
import com.yapp.bol.match.member.MatchMember
import com.yapp.bol.season.Season
import java.time.LocalDateTime

@JvmInline
value class MatchId(val value: Long)

data class Match(
    val id: MatchId = MatchId(0),
    val gameId: GameId,
    val groupId: GroupId,
    val matchedDate: LocalDateTime,
    val memberCount: Int,
    val season: Season,
    val matchMembers: List<MatchMember>
)
