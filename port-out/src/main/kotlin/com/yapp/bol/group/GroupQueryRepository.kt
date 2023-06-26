package com.yapp.bol.group

import com.yapp.bol.pagination.offset.PaginationOffsetResponse

interface GroupQueryRepository {
    fun findById(id: GroupId): Group?

    fun search(name: String?, pageNumber: Int, pageSize: Int): PaginationOffsetResponse<Group>
}
