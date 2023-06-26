package com.yapp.bol.group

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.JpaRepository

internal interface GroupRepository : JpaRepository<GroupEntity, Long> {
    fun findByNameLikeOrOrganizationLike(name: String, organization: String, pageable: Pageable): Slice<GroupEntity>
}
