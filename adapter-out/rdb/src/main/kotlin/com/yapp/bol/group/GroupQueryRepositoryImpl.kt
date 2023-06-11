package com.yapp.bol.group

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
internal class GroupQueryRepositoryImpl(
    private val groupRepository: GroupRepository,
) : GroupQueryRepository {
    override fun findById(id: Long): Group? {
        return groupRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun findGroupByName(name: String): Group? {
        return groupRepository.findByName(name)?.toDomain()
    }
}
