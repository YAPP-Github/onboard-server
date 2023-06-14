package com.yapp.bol.pageable

data class ApplicationSlice<T>(
    val content: List<T>,
    val hasNext: Boolean
)
