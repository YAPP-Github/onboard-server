package com.yapp.bol.pagination.cursor

interface CursorResponse<T, CURSOR> {
    val contents: List<T>
    val cursor: CURSOR
    val hasNext: Boolean
}
