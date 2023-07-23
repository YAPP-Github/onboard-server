package com.yapp.bol.game.member

import com.yapp.bol.InvalidMatchMemberException
import com.yapp.bol.game.GameId
import com.yapp.bol.group.member.MemberId
import com.yapp.bol.season.Season
import kotlin.math.floor
import kotlin.math.sqrt

@JvmInline
value class GameMemberId(val value: Long)

data class GameMember(
    val id: GameMemberId = GameMemberId(0),
    val gameId: GameId,
    val memberId: MemberId,
    val season: Season,
    val finalScore: Int,
    val matchCount: Int,
    val winningPercentage: Double
) {
    // TODO: 더 나은 네이밍
    fun processMatch(
        rank: Int,
        memberCount: Int
    ): GameMember {
        val finalScore = this.finalScore + this.calculateUpdatedScore(
            rank = rank,
            memberCount = memberCount
        )
        val matchCount = this.matchCount + 1

        return this.copy(
            finalScore = finalScore,
            matchCount = matchCount,
        )
    }

    private fun calculateUpdatedScore(rank: Int, memberCount: Int): Int {
        if (memberCount < MINIMUM_GAME_MEMBER_SIZE) {
            throw InvalidMatchMemberException
        }

        val threshold = floor((memberCount / 2).toDouble())

        if (rank > threshold) {
            return 0
        }

        return floor((MAX_SCORE / rank).toDouble() * sqrt(memberCount.toDouble())).toInt()
    }

    companion object {
        const val MINIMUM_GAME_MEMBER_SIZE = 2
        private const val DEFAULT_SCORE = 0
        private const val DEFAULT_MATCH_COUNT = 0
        private const val DEFAULT_WINNING_PERCENTAGE = 0.0
        private const val MAX_SCORE = 100

        fun of(
            gameId: GameId,
            memberId: MemberId,
            season: Season,
        ): GameMember {
            return GameMember(
                gameId = gameId,
                memberId = memberId,
                season = season,
                finalScore = DEFAULT_SCORE,
                matchCount = DEFAULT_MATCH_COUNT,
                winningPercentage = DEFAULT_WINNING_PERCENTAGE
            )
        }
    }
}
