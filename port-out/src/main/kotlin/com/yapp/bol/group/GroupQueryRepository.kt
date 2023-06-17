package com.yapp.bol.group

interface GroupQueryRepository {
    fun findById(id: GroupId): Group?

    fun findGroupByName(name: String): Group?
}
