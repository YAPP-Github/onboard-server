package com.yapp.bol.user

import com.yapp.bol.AuditingEntity
import com.yapp.bol.auth.UserId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
internal class UserEntity : AuditingEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id", nullable = false)
    var id: UserId = UserId(0)
        protected set

    @Column(name = "name")
    var name: String? = null
        protected set

    @Column(name = "deleted")
    var deleted: Boolean = false
        protected set

    companion object {
        fun of(id: UserId): UserEntity =
            UserEntity().apply {
                this.id = id
            }
    }
}
