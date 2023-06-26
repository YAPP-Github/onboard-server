package com.yapp.bol.pagination.offset

data class PaginationOffsetResponse<T>(
    val content: List<T>,
    val hasNext: Boolean
)
