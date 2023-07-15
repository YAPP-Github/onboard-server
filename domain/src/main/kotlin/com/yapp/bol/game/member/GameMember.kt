package com.yapp.bol.game.member

import com.yapp.bol.game.GameId
import com.yapp.bol.group.member.MemberId
import com.yapp.bol.season.Season

@JvmInline
value class GameMemberId(val value: Long)

class GameMember(
    val id: GameMemberId = GameMemberId(0),
    val gameId: GameId,
    val memberId: MemberId,
    val season: Season,
    finalScore: Int,
    matchCount: Int,
    winningPercentage: Double
) {
    var finalScore = finalScore
        private set

    var matchCount = matchCount
        private set

    var winningPercentage = winningPercentage
        private set

    fun processMatch(matchScore: Int) {
        updateScore(matchScore)
        addMatchCount()
    }

    private fun updateScore(matchScore: Int) {
        this.finalScore = this.finalScore + matchScore
    }

    private fun addMatchCount() {
        this.matchCount = this.matchCount + 1
    }

    companion object {
        private const val DEFAULT_SCORE = 0
        private const val DEFAULT_MATCH_COUNT = 0
        private const val DEFAULT_WINNING_PERCENTAGE = 0.0

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
