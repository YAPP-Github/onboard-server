package com.yapp.bol.game

import com.yapp.bol.base.ARRAY
import com.yapp.bol.base.ControllerTest
import com.yapp.bol.base.NUMBER
import com.yapp.bol.base.OpenApiTag
import com.yapp.bol.base.STRING
import io.mockk.every
import io.mockk.mockk

class GameControllerTest : ControllerTest() {
    private val gameService: GameService = mockk()
    override val controller = GameController(gameService)

    init {
        test("게임 목록 가져오기") {
            val groupId = 1L
            val games = listOf(
                Game(GameId(0), "게임 1", 2, 4, GameRankType.SCORE_HIGH, "ImgUrl"),
                Game(GameId(1), "게임 2", 2, 5, GameRankType.SCORE_HIGH, "ImgUrl"),
                Game(GameId(2), "게임 3", 1, 4, GameRankType.SCORE_HIGH, "ImgUrl")
            )
            every { gameService.getGameList(groupId) } returns games

            get("/v1/game/{groupId}", groupId.toString()) {}
                .isStatus(200)
                .makeDocument(
                    DocumentInfo(identifier = "game/{method-name}", tag = OpenApiTag.GAME),
                    pathParameters(
                        "groupId" type NUMBER means "게임 목록을 조회하고자 하는 groupId, 현재는 아무값이나 넣어도 검증 없이 동일하게 내려감"
                    ),
                    responseFields(
                        "list" type ARRAY means "게임 목록",
                        "list[].id" type NUMBER means "게임 ID",
                        "list[].name" type STRING means "게임 이름",
                        "list[].minMember" type NUMBER means "게임 최소 인원수",
                        "list[].maxMember" type NUMBER means "게임 최대 인원수",
                        "list[].img" type STRING means "게임 Img Url",
                    )
                )
        }
    }
}
