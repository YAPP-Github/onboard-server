package com.yapp.bol.game

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface GameRepository : JpaRepository<GameEntity, Long> {
    @Query("FROM GameEntity e JOIN FETCH e.img")
    fun getAll(): List<GameEntity>
}
