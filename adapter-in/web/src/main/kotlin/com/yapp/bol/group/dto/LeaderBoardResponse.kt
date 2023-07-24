package com.yapp.bol.group.dto

import com.yapp.bol.group.LeaderBoardMember
import com.yapp.bol.group.member.MemberId

data class LeaderBoardResponse(
    val contents: List<RankMemberResponse>
)

data class RankMemberResponse(
    val id: MemberId,
    val nickname: String,
    val rank: Int?,
    val score: Int?,
    val matchCount: Int?,
    val isChangeRecent: Boolean,
)

fun LeaderBoardMember.toResponse(rank: Int): RankMemberResponse = RankMemberResponse(
    id = this.member.id,
    nickname = this.member.nickname,
    rank = if (this.score == null) null else rank,
    score = this.score,
    matchCount = this.matchCount,
    isChangeRecent = this.isChangeRecent,
)
