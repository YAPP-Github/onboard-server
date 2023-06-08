package com.yapp.bol.group

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
internal class GroupCommandRepositoryImpl(
    private val groupRepository: GroupRepository,
) : GroupCommandRepository {
    @Transactional
    override fun createGroup(group: Group): Group {
        val groupEntity = groupRepository.save(group.toEntity())

        return groupEntity.toDomain()
    }
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
    members = members.map {
        Member(
            id = it.id,
            userId = it.userId,
            role = it.role,
            nickname = it.nickname,
        )
    }.let {
        MemberList(it.toMutableList())
    }
)
