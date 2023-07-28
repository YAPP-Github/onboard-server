package com.yapp.bol.auth.token

import org.springframework.data.jpa.repository.JpaRepository

internal interface RefreshTokenRepository : JpaRepository<RefreshTokenEntity, Long> {
    fun findByRefreshToken(refreshToken: ByteArray): RefreshTokenEntity?
    fun deleteByRefreshToken(refreshToken: ByteArray)
    fun deleteByUserId(userId: Long)
}
