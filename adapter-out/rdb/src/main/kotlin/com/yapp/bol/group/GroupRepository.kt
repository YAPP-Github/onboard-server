package com.yapp.bol.group

import com.yapp.bol.group.member.MemberList
import com.yapp.bol.group.member.toDomain
import com.yapp.bol.group.member.toEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal interface GroupRepository : JpaRepository<GroupEntity, Long> {
    fun findByName(name: String): GroupEntity?
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
