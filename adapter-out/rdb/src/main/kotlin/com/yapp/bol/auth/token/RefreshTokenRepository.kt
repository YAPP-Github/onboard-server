package com.yapp.bol.auth.token

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

internal interface RefreshTokenRepository : JpaRepository<RefreshTokenEntity, Long> {
    fun findByRefreshToken(refreshToken: ByteArray): RefreshTokenEntity?
    fun deleteByRefreshToken(refreshToken: ByteArray)

    @Modifying
    @Query("DELETE FROM RefreshTokenEntity e WHERE e.userId = :userId")
    fun deleteByUserId(userId: Long)
}
