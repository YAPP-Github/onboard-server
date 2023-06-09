package com.yapp.bol.group

import com.yapp.bol.group.member.toEntity
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
