package yapp.rating.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import yapp.rating.AuditingEntity

@Entity

@Table(name = "users")
internal class UserEntity : AuditingEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id", nullable = false)
    var id: Long = 0
        protected set

    @Column(name = "name")
    var name: String? = null
        protected set

    @Column(name = "deleted")
    var deleted: Boolean = false
        protected set

    companion object {
        fun of(id: Long): UserEntity =
            UserEntity().apply {
                this.id = id
            }
    }
}
