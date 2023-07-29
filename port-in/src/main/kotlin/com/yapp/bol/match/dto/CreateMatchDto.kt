package com.yapp.bol.match.dto

import com.yapp.bol.game.GameId
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.MemberId
import com.yapp.bol.match.Match
import com.yapp.bol.match.member.MatchMember
import com.yapp.bol.match.member.MatchMemberId
import com.yapp.bol.season.Season
import java.time.LocalDateTime

data class CreateMatchMemberDto(
    val memberId: MemberId,
    val score: Int,
    val ranking: Int,
)

data class CreateMatchDto(
    val gameId: GameId,
    val groupId: GroupId,
    val matchedDate: LocalDateTime,
    val createMatchMemberDtos: List<CreateMatchMemberDto>
)

fun CreateMatchDto.toDomain(season: Season): Match = Match(
    gameId = this.gameId,
    groupId = this.groupId,
    matchedDate = this.matchedDate,
    memberCount = this.createMatchMemberDtos.size,
    season = season,
    matchMembers = this.createMatchMemberDtos.map { it.toDomain() }
)

fun CreateMatchMemberDto.toDomain(): MatchMember = MatchMember(
    id = MatchMemberId(0),
    memberId = this.memberId,
    score = this.score,
    ranking = this.ranking
)
