package com.yapp.bol.match

import com.yapp.bol.auth.UserId
import com.yapp.bol.base.ARRAY
import com.yapp.bol.base.ControllerTest
import com.yapp.bol.base.NUMBER
import com.yapp.bol.base.OpenApiTag
import com.yapp.bol.base.STRING
import com.yapp.bol.game.GameId
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.MemberId
import com.yapp.bol.match.dto.CreateMatchRequest
import com.yapp.bol.match.dto.MatchMemberRequest
import com.yapp.bol.match.dto.MatchWithMatchMemberList
import com.yapp.bol.match.member.MatchMember
import com.yapp.bol.match.member.MatchMemberId
import com.yapp.bol.season.Season
import com.yapp.bol.season.SeasonId
import io.mockk.every
import io.mockk.mockk
import java.time.LocalDateTime

class MatchControllerTest : ControllerTest() {
    private val matchService: MatchService = mockk()
    override val controller = MatchController(matchService)

    init {
        test("매치 기록하기") {
            val matchMembers = listOf(
                MatchMemberRequest(
                    memberId = 1,
                    score = 1,
                    ranking = 1,
                ),
                MatchMemberRequest(
                    memberId = 2,
                    score = 2,
                    ranking = 2,
                ),
                MatchMemberRequest(
                    memberId = 3,
                    score = 3,
                    ranking = 3,
                ),
                MatchMemberRequest(
                    memberId = 4,
                    score = 4,
                    ranking = 4,
                ),
            )

            val request = CreateMatchRequest(
                gameId = 1,
                groupId = 1,
                matchedDate = LocalDateTime.now(),
                matchMembers = matchMembers
            )

            every {
                matchService.createMatch(any())
            } returns MATCH_WITH_MATCH_MEMBER_LIST

            post("/v1/match", request) {
                authorizationHeader(UserId(1))
            }
                .isStatus(200)
                .makeDocument(
                    DocumentInfo(identifier = "group/{method-name}", tag = OpenApiTag.MATCH),
                    requestFields(
                        "gameId" type NUMBER means "game id",
                        "groupId" type NUMBER means "group id",
                        "matchedDate" type STRING means "match 시간",
                        "matchMembers" type ARRAY means "match 멤버 목록",
                        "matchMembers[].memberId" type NUMBER means "member id",
                        "matchMembers[].score" type NUMBER means "점수",
                        "matchMembers[].ranking" type NUMBER means "순위",
                    ),
                )
        }
    }

    companion object {
        private val SEASON = Season(
            id = SeasonId(1),
            groupId = GroupId(1),
        )

        private val MATCH = Match(
            id = MatchId(1),
            gameId = GameId(1),
            groupId = GroupId(1),
            matchedDate = LocalDateTime.now(),
            memberCount = 1,
            season = SEASON
        )

        private val MATCH_MEMBERS = listOf(
            MatchMember(
                id = MatchMemberId(1),
                matchId = MATCH.id,
                memberId = MemberId(1),
                score = 1,
                ranking = 1,
                previousScore = 0
            ),
            MatchMember(
                id = MatchMemberId(2),
                matchId = MATCH.id,
                memberId = MemberId(2),
                score = 2,
                ranking = 2,
                previousScore = 0
            ),
            MatchMember(
                id = MatchMemberId(3),
                matchId = MATCH.id,
                memberId = MemberId(3),
                score = 3,
                ranking = 3,
                previousScore = 0
            ),
            MatchMember(
                id = MatchMemberId(4),
                matchId = MATCH.id,
                memberId = MemberId(4),
                score = 4,
                ranking = 4,
                previousScore = 0
            ),
        )

        private val MATCH_WITH_MATCH_MEMBER_LIST = MatchWithMatchMemberList(
            match = MATCH,
            matchMembers = MATCH_MEMBERS
        )
    }
}
