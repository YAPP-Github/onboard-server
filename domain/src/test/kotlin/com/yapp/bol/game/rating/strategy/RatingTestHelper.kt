package com.yapp.bol.game.rating.strategy

import com.yapp.bol.game.rating.dto.MatchOutcome
import com.yapp.bol.game.rating.dto.RatingInput
import com.yapp.bol.group.member.MemberId

object RatingTestHelper {
    fun creatMatchTestFixture(vararg finalScore: Int): List<RatingInput> {
        val outcomes = finalScore.mapIndexed { index, score ->
            val ranking = index + 1
            MatchOutcome(
                memberId = MemberId(ranking.toLong()),
                score = 100 / ranking,
                ranking = ranking,
                finalScore = score
            )
        }

        return outcomes.map { me ->
            val opponents = outcomes.filter { it.memberId != me.memberId }
            RatingInput(me = me, opponents = opponents)
        }
    }
}
