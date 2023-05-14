package yapp.rating.auth.token

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import yapp.rating.AuditingEntity

@Entity
@Table(name = "auth_refresh_token")
internal class RefreshTokenEntity(
    userId: Long,
    refreshToken: ByteArray,
    expireAt: LocalDateTime,
) : AuditingEntity() {
    @Id
    @Column(name = "refresh_token_id")
    var id: Long = 0
        protected set

    @Column(name = "user_id")
    var userId: Long = userId
        protected set

    @Column(name = "refresh_token")
    var refreshToken: ByteArray = refreshToken
        protected set

    @Column(name = "expired_at")
    lateinit var expiredAt: LocalDateTime
        protected set
}
