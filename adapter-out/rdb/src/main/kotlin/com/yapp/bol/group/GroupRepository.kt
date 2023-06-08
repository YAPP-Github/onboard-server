package com.yapp.bol.group

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal interface GroupRepository : JpaRepository<GroupEntity, Long> {
    fun findByName(name: String): GroupEntity?
}
