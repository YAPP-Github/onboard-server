package com.yapp.bol.pagination.offset

data class PaginationCursor<T>(
    val content: List<T>,
    val hasNext: Boolean
)
