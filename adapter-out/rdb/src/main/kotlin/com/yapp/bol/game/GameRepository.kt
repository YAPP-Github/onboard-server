package com.yapp.bol.game

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface GameRepository : JpaRepository<GameEntity, Long> {
    @Query("FROM GameEntity e JOIN FETCH e.img")
    fun getAll(): List<GameEntity>

    @Query(
        "SELECT g.id, COUNT(m) AS cnt FROM GameEntity g " +
            "LEFT JOIN MatchEntity m ON m.gameId = g.id " +
            "WHERE m.groupId = :groupId " +
            "GROUP BY g.id " +
            "ORDER BY cnt DESC"
    )
    fun getMatchCount(groupId: Long): List<List<Any>>
}
