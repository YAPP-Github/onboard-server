package com.yapp.bol.group.dto

import com.yapp.bol.auth.UserId
import com.yapp.bol.group.LeaderBoardMember
import com.yapp.bol.group.member.MemberId
import com.yapp.bol.group.member.MemberRole

data class LeaderBoardResponse(
    val contents: List<RankMemberResponse>
)

data class RankMemberResponse(
    @Deprecated("memberId로 대체 사용") val id: MemberId,
    val memberId: MemberId,
    val userId: UserId?,
    val nickname: String,
    val role: MemberRole,
    val rank: Int?,
    val score: Int?,
    val matchCount: Int?,
    val isChangeRecent: Boolean,
)

fun LeaderBoardMember.toResponse(rank: Int): RankMemberResponse = RankMemberResponse(
    id = this.member.id,
    memberId = this.member.id,
    userId = this.member.userId,
    nickname = this.member.nickname,
    role = this.member.role,
    rank = if (this.score == null) null else rank,
    score = this.score,
    matchCount = this.matchCount,
    isChangeRecent = this.isChangeRecent,
)
