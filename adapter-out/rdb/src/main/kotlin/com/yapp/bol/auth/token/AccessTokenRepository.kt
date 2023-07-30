package com.yapp.bol.auth.token

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

internal interface AccessTokenRepository : JpaRepository<AccessTokenEntity, Long> {
    fun findByAccessToken(accessToken: ByteArray): AccessTokenEntity?
    @Modifying
    @Query("DELETE FROM AccessTokenEntity e WHERE e.userId = :userId")
    fun deleteByUserId(userId: Long)
}
