package com.yapp.bol.user

import com.yapp.bol.AuditingEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Where

@Entity
@Table(name = "users")
@Where(clause = "deleted = false")
internal class UserEntity : AuditingEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id", nullable = false)
    var id: Long = 0
        protected set

    @Column(name = "name")
    var name: String? = null

    @Column(name = "deleted")
    var deleted: Boolean = false
        protected set

    fun delete() {
        name = null
        deleted = true
    }

    companion object {
        fun of(id: Long): UserEntity =
            UserEntity().apply {
                this.id = id
            }
    }
}
