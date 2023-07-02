package com.yapp.bol.group

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

internal interface GroupRepository : JpaRepository<GroupEntity, Long> {
    fun findByNameLike(name: String, pageable: Pageable): Slice<GroupEntity>

    @Query("SELECT g FROM MemberEntity m JOIN FETCH GroupEntity g ON m.groupId = g.id WHERE m.userId=:userId")
    fun findByUserId(userId: Long): List<GroupEntity>
}
