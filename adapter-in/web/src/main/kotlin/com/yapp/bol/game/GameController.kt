package com.yapp.bol.game

import com.yapp.bol.game.dto.GameListResponse
import com.yapp.bol.game.dto.toResponse
import com.yapp.bol.group.GroupId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/group")
class GameController(
    private val gameService: GameService,
) {

    @GetMapping("/{groupId}/game")
    fun groupId(
        @PathVariable("groupId") groupId: GroupId,
    ): GameListResponse {
        val games = gameService.getGameList(groupId)

        return GameListResponse(
            games.map { it.toResponse() }
        )
    }
}
