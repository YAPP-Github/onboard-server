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
    override fun findById(id: Long): Group? {
        return groupRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun findByNameLike(name: String, pageNumber: Int, pageSize: Int): PaginationCursor<Group> {
        val pageable = PageRequest.of(pageNumber, pageSize)

        val slice: Slice<GroupEntity> = groupRepository.findByNameLike("%$name%", pageable)
        val content: List<Group> = slice.content.map(GroupEntity::toDomain)

        return PaginationCursor(content, slice.hasNext())
    }
}
