package yapp.rating.auth.token

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import yapp.rating.AuditingEntity

@Entity
@Table(name = "auth_access_token")
internal class AccessTokenEntity : AuditingEntity() {
    @Id
    @Column(name = "access_token_id")
    var id: Long = 0
        protected set

    @Column(name = "user_id")
    var userId: Long = 0
        protected set

    @Column(name = "access_token")
    lateinit var accessToken: ByteArray
        protected set

    @Column(name = "expired_at")
    lateinit var expiredAt: LocalDateTime
        protected set
}
