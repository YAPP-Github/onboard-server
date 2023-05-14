package yapp.rating.auth.token

import org.springframework.data.jpa.repository.JpaRepository

interface AccessTokenRepository : JpaRepository<AccessTokenEntity, Long>
