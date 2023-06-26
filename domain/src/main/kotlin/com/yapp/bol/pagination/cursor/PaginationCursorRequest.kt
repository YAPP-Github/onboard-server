package com.yapp.bol.pagination.cursor

interface PaginationCursorRequest<CURSOR> {
    val size: Int
    val cursor: CURSOR?
}
