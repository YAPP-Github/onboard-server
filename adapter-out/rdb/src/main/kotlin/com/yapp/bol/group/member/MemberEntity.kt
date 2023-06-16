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
    id: MemberId = MemberId(0),
    userId: UserId? = null,
    groupId: GroupId = GroupId(0),
    role: MemberRole,
    nickname: String
) :
    AuditingEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    val id: MemberId = id

    @Column(name = "users_id")
    val userId: UserId? = userId

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val role: MemberRole = role

    @Column(name = "nickname")
    val nickname: String = nickname

    @Column(name = "deleted")
    val deleted: Boolean = false

    @Column(name = "group_id", nullable = false)
    val groupId: GroupId = groupId
}

fun MemberEntity.toDomain(): Member =
    if (userId == null) {
        GuestMember(
            id = this.id,
            nickname = this.nickname,
        )
    } else if (this.role == MemberRole.OWNER) {
        OwnerMember(
            id = this.id,
            userId = this.userId,
            nickname = this.nickname,
        )
    } else {
        HostMember(
            id = this.id,
            userId = this.userId,
            nickname = this.nickname,
        )
    }

fun Member.toEntity(groupId: GroupId): MemberEntity = MemberEntity(
    id = this.id,
    userId = this.userId,
    role = this.role,
    nickname = this.nickname,
    groupId = groupId
)
