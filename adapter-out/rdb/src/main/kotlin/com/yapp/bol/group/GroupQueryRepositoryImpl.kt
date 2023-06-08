package com.yapp.bol.group

import org.springframework.stereotype.Repository

@Repository
internal class GroupQueryRepositoryImpl(
    private val groupRepository: GroupRepository,
) : GroupQueryRepository {
    override fun findGroupByName(name: String): Group? {
        return groupRepository.findByName(name)?.toDomain()
    }
}
