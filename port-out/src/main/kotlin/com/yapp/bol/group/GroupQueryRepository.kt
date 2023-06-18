package com.yapp.bol.group

import com.yapp.bol.pageable.PaginationCursor

interface GroupQueryRepository {
    fun findById(id: Long): Group?

    fun findByNameLike(name: String?, pageNumber: Int, pageSize: Int): PaginationCursor<Group>
}
