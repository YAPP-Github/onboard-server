package com.yapp.bol.game

@JvmInline
value class GameId(val value: Long)

data class Game(
    val id: GameId,
    val name: String,
    val minMember: Int,
    val maxMember: Int,
    val rankType: GameRankType,
    val img: String,
)
