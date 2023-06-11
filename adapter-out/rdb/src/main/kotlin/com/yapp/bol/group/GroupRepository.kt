package com.yapp.bol.group

import org.springframework.data.jpa.repository.JpaRepository

internal interface GroupRepository : JpaRepository<GroupEntity, Long> {
    fun findByName(name: String): GroupEntity?
}
