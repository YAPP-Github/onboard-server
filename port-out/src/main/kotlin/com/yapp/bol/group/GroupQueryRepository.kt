package com.yapp.bol.group

interface GroupQueryRepository {
    fun findById(id: Long): Group?

    fun findGroupByName(name: String): Group?
}
