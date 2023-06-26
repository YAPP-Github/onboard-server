package com.yapp.bol.pagination.cursor

interface CursorRequest<CURSOR> {
    val size: Int
    val cursor: CURSOR?
}
