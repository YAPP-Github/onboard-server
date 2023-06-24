package com.yapp.bol.pagination

data class SimpleCursorResponse<T, CURSOR>(
    override val contents: List<T>,
    override val cursor: CURSOR,
    override val hasNext: Boolean,
) : CursorResponse<T, CURSOR> {

    fun <U> map(func: (T) -> U): SimpleCursorResponse<U, CURSOR> {
        return SimpleCursorResponse(
            contents = contents.map(func),
            cursor = cursor,
            hasNext = hasNext,
        )
    }
}
