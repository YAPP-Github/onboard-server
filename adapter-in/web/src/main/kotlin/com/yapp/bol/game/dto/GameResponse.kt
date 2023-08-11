package com.yapp.bol.game.dto

import com.yapp.bol.game.GameId
import com.yapp.bol.game.GameWithMatchCount

data class GameResponse(
    val id: GameId,
    val name: String,
    val minMember: Int,
    val maxMember: Int,
    val img: String,
    val matchCount: Int,
)

fun GameWithMatchCount.toResponse(): GameResponse = GameResponse(
    id = this.id,
    name = this.name,
    minMember = this.minMember,
    maxMember = this.maxMember,
    img = this.img,
    matchCount = this.matchCount,
)
