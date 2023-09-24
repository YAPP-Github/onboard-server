package com.yapp.bol.game.rating.dto

import com.yapp.bol.group.member.MemberId

data class RatingInput(
    val me: MatchOutcome,
    val opponents: List<MatchOutcome>
)

data class MatchOutcome(
    val memberId: MemberId,
    val score: Int,
    val ranking: Int,
    val finalScore: Int
)
