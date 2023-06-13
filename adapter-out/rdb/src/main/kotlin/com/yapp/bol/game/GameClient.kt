package com.yapp.bol.game

import org.springframework.stereotype.Component

@Component
class GameClient(
    private val gameRepository: GameRepository,
) : GameQueryRepository {
    override fun getGameListByGroupId(groupId: Long): List<Game> {
        return gameRepository.getAll().map { it.toDomain() }
    }

    private fun GameEntity.toDomain(): Game = Game(
        id = GameId(this.id),
        name = this.name,
        minMember = this.minMember,
        maxMember = this.maxMember,
        rankType = this.rankType,
        img = img.name,
    )
}
