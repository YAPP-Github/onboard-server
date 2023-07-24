package com.yapp.bol.group.dto

import com.yapp.bol.group.LeaderBoardMember
import com.yapp.bol.group.member.MemberId
import kotlin.random.Random

data class LeaderBoardResponse(
    val contents: List<RankMemberResponse>
)

data class RankMemberResponse(
    val id: MemberId,
    val nickname: String,
    val rank: Int?,
    val winningPercentage: Double?,
    val matchCount: Int?,
    val isChangeRecent: Boolean,
)

fun LeaderBoardMember.toResponse(rank: Int): RankMemberResponse = RankMemberResponse(
    id = this.member.id,
    nickname = this.member.nickname,
    rank = rank,
    winningPercentage = this.winningPercentage,
    matchCount = this.matchCount,
    isChangeRecent = this.isChangeRecent,
)
