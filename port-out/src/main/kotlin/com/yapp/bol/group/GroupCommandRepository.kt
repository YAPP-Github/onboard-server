package com.yapp.bol.group

interface GroupCommandRepository {
    fun createGroup(group: Group): Group
}
