package com.yapp.bol.game.rating.strategy

import com.yapp.bol.game.rating.dto.RatingInput
import kotlin.math.roundToInt

object EloRatingStrategy : RatingStrategy {

    const val K = 32

    override fun compute(input: RatingInput): Int {
        val me = input.me
        val opponents = input.opponents

        if (opponents.isEmpty()) {
            return me.finalScore
        }

        val eloSum = opponents.fold(0.0) { acc, opponent ->
            // 1 on 1 elo 점수의 합
            val expectedOutcome = computeExpectedOutcome(input.me.finalScore, opponent.finalScore)
            val actualOutcome = computeActualOutcome(input.me.ranking, opponent.ranking)

            acc + me.finalScore + K * (actualOutcome - expectedOutcome)
        }

        if (eloSum < 0) {
            return 0
        }

        return (eloSum / opponents.size).roundToInt()
    }

    private fun computeExpectedOutcome(myElo: Int, opponentElo: Int): Double {
        return 1.0 / (1.0 + Math.pow(10.0, ((opponentElo - myElo) / 400.0)))
    }

    private fun computeActualOutcome(myRanking: Int, opponentRanking: Int): Double {
        when {
            myRanking < opponentRanking -> return 1.0 // 승리
            myRanking == opponentRanking -> return 0.5 // 무승부
            else -> return 0.0 // 패배
        }
    }
}
