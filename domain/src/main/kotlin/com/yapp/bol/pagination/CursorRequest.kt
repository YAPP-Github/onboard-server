package com.yapp.bol.pagination

interface CursorRequest<CURSOR> {
    val size: Int
    val cursor: CURSOR?
}
