package com.yapp.bol.game

@JvmInline
value class GameId(val value: Long)

data class GameWithMatchCount(
    val game: Game,
    val matchCount: Int,
) : GameBasicInfo by game

data class Game(
    override val id: GameId,
    override val name: String,
    override val minMember: Int,
    override val maxMember: Int,
    override val rankType: GameRankType,
    override val img: String,
) : GameBasicInfo

interface GameBasicInfo {
    val id: GameId
    val name: String
    val minMember: Int
    val maxMember: Int
    val rankType: GameRankType
    val img: String
}
