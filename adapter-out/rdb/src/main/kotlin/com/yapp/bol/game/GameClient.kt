package com.yapp.bol.game

import com.yapp.bol.file.FileNameConverter
import com.yapp.bol.group.GroupId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class GameClient(
    private val gameRepository: GameRepository,
) : GameQueryRepository {
    override fun getGameListByGroupId(groupId: GroupId): List<GameWithMatchCount> {
        val games = gameRepository.getAll()

        val matchCounts = gameRepository.getMatchCount(groupId.value).associate {
            val gameId = it[0] as Long
            val count = (it[1] as Long).toInt()

            gameId to count
        }

        return games.map {
            GameWithMatchCount(it.toDomain(), matchCounts[it.id] ?: 0)
        }.sortedByDescending { it.matchCount }
    }

    override fun findById(id: GameId): Game? {
        return gameRepository.findByIdOrNull(id.value)?.toDomain()
    }

    private fun GameEntity.toDomain(): Game = Game(
        id = GameId(this.id),
        name = this.name,
        minMember = this.minMember,
        maxMember = this.maxMember,
        rankType = this.rankType,
        img = FileNameConverter.convertFileUrl(img.name),
    )
}
