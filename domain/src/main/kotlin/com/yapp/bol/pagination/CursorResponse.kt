package com.yapp.bol.pagination

interface CursorResponse<T, CURSOR> {
    val contents: List<T>
    val cursor: CURSOR
    val hasNext: Boolean
}
