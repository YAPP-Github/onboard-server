package com.yapp.bol.pagination.cursor

data class SimplePaginationCursorResponse<T, CURSOR>(
    override val contents: List<T>,
    override val cursor: CURSOR,
    override val hasNext: Boolean,
) : PaginationCursorResponse<T, CURSOR> {

    fun <U> mapContents(func: (T) -> U): SimplePaginationCursorResponse<U, CURSOR> {
        return SimplePaginationCursorResponse(
            contents = contents.map(func),
            cursor = cursor,
            hasNext = hasNext,
        )
    }
}
