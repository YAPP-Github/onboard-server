package com.yapp.bol.game.member

import com.yapp.bol.game.GameId
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.MemberId
import com.yapp.bol.season.Season
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class GameMemberTest : FunSpec() {
    private val gameId = GameId(0)

    private val memberId = MemberId(0)

    private val groupId = GroupId(0)

    private val season = Season(groupId = groupId)

    init {
        test("매치 점수 처리") {
            val gameMember = GameMember.of(gameId, memberId, season)

            val currentScore = 10
            val initialScore = gameMember.finalScore
            val initialMatchCount = gameMember.matchCount

            gameMember.processMatch(currentScore)

            gameMember.finalScore shouldBe initialScore + currentScore
            gameMember.matchCount shouldBe initialMatchCount + 1
        }
    }
}
