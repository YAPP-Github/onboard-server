package com.yapp.bol.group.member

import com.yapp.bol.AuditingEntity
import com.yapp.bol.auth.UserId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Where

@Entity
@Table(name = "member")
@Where(clause = "deleted = false")
class MemberEntity(
    id: Long = 0,
    userId: Long? = null,
    groupId: Long = 0,
    role: MemberRole,
    nickname: String
) : AuditingEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    val id: Long = id

    @Column(name = "users_id")
    val userId: Long? = userId

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val role: MemberRole = role

    @Column(name = "nickname")
    var nickname: String? = nickname
        protected set

    @Column(name = "level")
    val level: Int = 0

    @Column(name = "deleted")
    var deleted: Boolean = false
        protected set

    @Column(name = "group_id", nullable = false)
    val groupId: Long = groupId

    fun delete() {
        nickname = null
        deleted = true
    }
}

fun MemberEntity.toDomain(): Member {
    if (this.userId == null) return toGuestMember()

    return when (this.role) {
        MemberRole.GUEST -> toGuestMember()
        MemberRole.HOST -> HostMember(
            id = MemberId(this.id),
            userId = UserId(this.userId),
            nickname = this.nickname ?: DELETE_MEMBER_NICKNAME,
            level = this.level,
        )

        MemberRole.OWNER -> OwnerMember(
            id = MemberId(this.id),
            userId = UserId(this.userId),
            nickname = this.nickname ?: DELETE_MEMBER_NICKNAME,
            level = this.level,
        )
    }
}

private fun MemberEntity.toGuestMember(): GuestMember =
    GuestMember(
        id = MemberId(this.id),
        nickname = this.nickname ?: DELETE_MEMBER_NICKNAME,
        level = this.level,
    )

fun Member.toEntity(groupId: Long): MemberEntity = MemberEntity(
    id = this.id.value,
    userId = this.userId?.value,
    role = this.role,
    nickname = this.nickname,
    groupId = groupId,
)

private const val DELETE_MEMBER_NICKNAME = "탈퇴한 맴버"
