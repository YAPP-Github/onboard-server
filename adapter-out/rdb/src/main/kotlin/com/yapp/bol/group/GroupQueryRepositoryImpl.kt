package com.yapp.bol.group

import org.springframework.stereotype.Repository

@Repository
internal class GroupQueryRepositoryImpl(
    private val groupRepository: GroupRepository,
) : GroupQueryRepository {
    override fun findById(id: Long): Group? {
        return groupRepository.findById(id)
            .orElse(null)
            ?.let {
                it.toDomain()
            }
    }

    override fun findGroupByName(name: String): Group? {
        return groupRepository.findByName(name)?.toDomain()
    }
}
