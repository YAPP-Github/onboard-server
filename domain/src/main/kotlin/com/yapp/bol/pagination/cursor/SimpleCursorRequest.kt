package com.yapp.bol.pagination.cursor

data class SimpleCursorRequest<CURSOR>(
    override val size: Int,
    override val cursor: CURSOR?,
) : CursorRequest<CURSOR>
