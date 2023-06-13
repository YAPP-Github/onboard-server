package com.yapp.bol.game

import com.yapp.bol.game.dto.GameListResponse
import com.yapp.bol.game.dto.toResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/game")
class GameController(
    private val gameService: GameService,
) {

    @GetMapping("/{groupId}")
    fun groupId(
        @PathVariable("groupId") groupId: Long,
    ): GameListResponse {
        val games = gameService.getGameList(groupId)

        return GameListResponse(
            games.map { it.toResponse() }
        )
    }
}
