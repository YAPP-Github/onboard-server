package com.yapp.bol.group.dto

import com.yapp.bol.group.member.MemberId

data class LeaderBoardResponse(
    val contents: List<RankMemberResponse>
)

data class RankMemberResponse(
    val id: MemberId,
    val rank: Int,
    val name: String,
    val winningPercentage: Double,
    val playCount: Int,
)
