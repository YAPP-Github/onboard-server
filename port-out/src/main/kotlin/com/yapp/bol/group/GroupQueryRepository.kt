package com.yapp.bol.group

import com.yapp.bol.pagination.offset.PaginationCursor

interface GroupQueryRepository {
    fun findById(id: GroupId): Group?

    fun findByNameLike(name: String?, pageNumber: Int, pageSize: Int): PaginationCursor<Group>
}
