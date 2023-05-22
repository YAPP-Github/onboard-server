package com.yapp.bol.auth.token

import org.springframework.data.jpa.repository.JpaRepository

internal interface RefreshTokenRepository : JpaRepository<RefreshTokenEntity, Long>
