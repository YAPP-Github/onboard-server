package com.yapp.bol.pagination.cursor

data class SimplePaginationCursorRequest<CURSOR>(
    override val size: Int,
    override val cursor: CURSOR?,
) : PaginationCursorRequest<CURSOR>
