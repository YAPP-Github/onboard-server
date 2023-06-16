package com.yapp.bol.group.member

import com.yapp.bol.AuditingEntity
import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "member")
class MemberEntity(
    id: Long = 0,
    userId: Long? = null,
    groupId: Long = 0,
    role: MemberRole,
    nickname: String
) :
    AuditingEntity() {
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
    val nickname: String = nickname

    @Column(name = "deleted")
    val deleted: Boolean = false

    @Column(name = "group_id", nullable = false)
    val groupId: Long = groupId
}

fun MemberEntity.toDomain(): Member =
    if (userId == null) {
        GuestMember(
            id = MemberId(this.id),
            groupId = GroupId(this.groupId),
            nickname = this.nickname,
        )
    } else if (this.role == MemberRole.OWNER) {
        OwnerMember(
            id = MemberId(this.id),
            userId = UserId(this.userId),
            groupId = GroupId(this.groupId),
            nickname = this.nickname,
        )
    } else {
        HostMember(
            id = MemberId(this.id),
            userId = UserId(this.userId),
            groupId = GroupId(this.groupId),
            nickname = this.nickname,
        )
    }

fun Member.toEntity(): MemberEntity = MemberEntity(
    id = this.id.value,
    userId = this.userId?.value,
    role = this.role,
    nickname = this.nickname,
    groupId = this.groupId.value,
)
