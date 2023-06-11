package com.yapp.bol.group

import com.yapp.bol.AuditingEntity
import com.yapp.bol.group.member.MemberEntity
import com.yapp.bol.group.member.MemberList
import com.yapp.bol.group.member.toDomain
import com.yapp.bol.group.member.toEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "group_table")
internal class GroupEntity(
    id: Long = 0,
    name: String,
    description: String,
    organization: String,
    profileImageUrl: String,
    accessCode: String,
    members: List<MemberEntity>,
) : AuditingEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false)
    val id: Long = id

    @Column(name = "name")
    val name: String = name

    @Column(name = "description")
    val description: String = description

    @Column(name = "organization")
    val organization: String = organization

    @Column(name = "profileImageUrl")
    val profileImageUrl: String = profileImageUrl

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    val members: List<MemberEntity> = members

    @Column(name = "access_code")
    val accessCode: String = accessCode

    @Column(name = "deleted")
    val deleted: Boolean = false
}

internal fun Group.toEntity(): GroupEntity = GroupEntity(
    id = id,
    name = name,
    description = description,
    organization = organization,
    profileImageUrl = profileImageUrl,
    accessCode = accessCode,
    members = members.toList().map {
        it.toEntity()
    }
)

internal fun GroupEntity.toDomain(): Group = Group(
    id = id,
    name = name,
    description = description,
    organization = organization,
    profileImageUrl = profileImageUrl,
    accessCode = accessCode,
    members = MemberList(
        members.map { it.toDomain() }.toMutableList()
    )
)
