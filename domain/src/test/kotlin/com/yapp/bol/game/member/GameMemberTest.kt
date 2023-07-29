package com.yapp.bol.game.member

import com.yapp.bol.game.GameId
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.MemberId
import com.yapp.bol.season.Season
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlin.math.floor
import kotlin.math.sqrt

class GameMemberTest : FunSpec() {
    private val gameId = GameId(0)

    private val groupId = GroupId(0)

    private val season = Season(groupId = groupId)

    init {
        test("처음 게임하는 두 명이 매치한 경우") {
            val memberId1 = MemberId(1)
            val memberId2 = MemberId(2)
            val memberCount = 2

            val gameMember1 = GameMember.of(gameId = gameId, memberId = memberId1, season = season)
                .generateWithNewMatch(rank = 1, memberCount = 2)
            val gameMember2 = GameMember.of(gameId = gameId, memberId = memberId2, season = season)
                .generateWithNewMatch(rank = 2, memberCount = 2)

            listOf(gameMember1, gameMember2).forEach {
                it.matchCount shouldBe 1
            }

            gameMember1.finalScore shouldBe floor(100 * sqrt(memberCount.toDouble()))
            gameMember2.finalScore shouldBe 0
        }

        test("두 명이 매치한 경우") {
            val score1 = 300
            val matchCount1 = 5
            val score2 = 15
            val matchCount2 = 10
            val memberCount = 2

            val memberId1 = MemberId(1)
            val memberId2 = MemberId(2)

            val gameMember1 = GameMember(
                gameId = gameId,
                memberId = memberId1,
                season = season,
                finalScore = score1,
                matchCount = matchCount1,
                winningPercentage = 0.0
            )
                .generateWithNewMatch(rank = 1, memberCount = memberCount)

            val gameMember2 = GameMember(
                gameId = gameId,
                memberId = memberId2,
                season = season,
                finalScore = score2,
                matchCount = matchCount2,
                winningPercentage = 0.0
            )
                .generateWithNewMatch(rank = 2, memberCount = 2)

            gameMember1.matchCount shouldBe matchCount1 + 1
            gameMember2.matchCount shouldBe matchCount2 + 1

            gameMember1.finalScore shouldBe score1 + floor(100 * sqrt(memberCount.toDouble()))
            gameMember2.finalScore shouldBe score2 + 0
        }

        test("처음 게임하는 5명이 매치한 경우") {
            val memberId1 = MemberId(1)
            val memberId2 = MemberId(2)
            val memberId3 = MemberId(3)
            val memberId4 = MemberId(4)
            val memberId5 = MemberId(5)
            val memberCount = 5

            val gameMember1 = GameMember.of(gameId = gameId, memberId = memberId1, season = season)
                .generateWithNewMatch(rank = 1, memberCount = memberCount)
            val gameMember2 = GameMember.of(gameId = gameId, memberId = memberId2, season = season)
                .generateWithNewMatch(rank = 2, memberCount = memberCount)
            val gameMember3 = GameMember.of(gameId = gameId, memberId = memberId3, season = season)
                .generateWithNewMatch(rank = 3, memberCount = memberCount)
            val gameMember4 = GameMember.of(gameId = gameId, memberId = memberId4, season = season)
                .generateWithNewMatch(rank = 4, memberCount = memberCount)
            val gameMember5 = GameMember.of(gameId = gameId, memberId = memberId5, season = season)
                .generateWithNewMatch(rank = 5, memberCount = memberCount)

            listOf(
                gameMember1,
                gameMember2,
                gameMember3,
                gameMember4,
                gameMember5
            ).forEach {
                it.matchCount shouldBe 1
            }

            gameMember1.finalScore shouldBe floor(100 * sqrt(memberCount.toDouble()))
            gameMember2.finalScore shouldBe floor(50 * sqrt(memberCount.toDouble()))
            gameMember3.finalScore shouldBe 0
            gameMember4.finalScore shouldBe 0
            gameMember5.finalScore shouldBe 0
        }
    }
}
