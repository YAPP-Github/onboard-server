package yapp.rating.auth.token

import org.springframework.data.jpa.repository.JpaRepository

internal interface RefreshTokenRepository : JpaRepository<RefreshTokenEntity, Long>
