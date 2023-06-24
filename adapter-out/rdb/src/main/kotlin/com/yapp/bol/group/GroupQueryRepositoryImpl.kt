package com.yapp.bol.group

import com.yapp.bol.pageable.PaginationCursor
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Slice
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
internal class GroupQueryRepositoryImpl(
    private val groupRepository: GroupRepository,
) : GroupQueryRepository {
    override fun findById(id: GroupId): Group? {
        return groupRepository.findByIdOrNull(id.value)?.toDomain()
    }

    override fun findByNameLike(name: String?, pageNumber: Int, pageSize: Int): PaginationCursor<Group> {
        val pageable = PageRequest.of(pageNumber, pageSize)

        if (name.isNullOrEmpty()) {
            val groups: Slice<GroupEntity> = groupRepository.findAll(pageable)

            return toCursor(groups)
        }

        val groups: Slice<GroupEntity> = groupRepository.findByNameLike("%$name%", pageable)

        return toCursor(groups)
    }

    private fun toCursor(slice: Slice<GroupEntity>): PaginationCursor<Group> {
        val content: List<Group> = slice.content.map(GroupEntity::toDomain)

        return PaginationCursor(content, slice.hasNext())
    }
}
