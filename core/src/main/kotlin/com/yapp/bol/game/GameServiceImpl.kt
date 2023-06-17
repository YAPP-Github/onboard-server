package com.yapp.bol.game

import org.springframework.stereotype.Service

@Service
internal class GameServiceImpl(
    private val gameQueryRepository: GameQueryRepository,
) : GameService {
    override fun getGameList(groupId: Long): List<Game> {
        return gameQueryRepository.getGameListByGroupId(groupId)
    }
}
