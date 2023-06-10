package com.yapp.bol.group

interface GroupQueryRepository {
    fun findGroupByName(name: String): Group?
}
