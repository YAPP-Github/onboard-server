package com.yapp.bol.group

import com.yapp.bol.AuditingEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "group_table")
internal class GroupEntity(
    id: GroupId = GroupId(0),
    name: String,
    description: String,
    organization: String?,
    profileImageUrl: String,
    accessCode: String,
) : AuditingEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false)
    val id: Long = id.value

    @Column(name = "name")
    val name: String = name

    @Column(name = "description")
    val description: String = description

    @Column(name = "organization")
    val organization: String? = organization

    @Column(name = "profileImageUrl")
    val profileImageUrl: String = profileImageUrl

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
)

internal fun GroupEntity.toDomain(): Group = Group(
    id = GroupId(id),
    name = name,
    description = description,
    organization = organization,
    profileImageUrl = profileImageUrl,
    accessCode = accessCode,
)
