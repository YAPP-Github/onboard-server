package com.yapp.bol.pagination.cursor

interface PaginationCursorResponse<T, CURSOR> {
    val contents: List<T>
    val cursor: CURSOR
    val hasNext: Boolean
}
