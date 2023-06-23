package com.yapp.bol.group.member

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

internal interface MemberRepository : JpaRepository<MemberEntity, Long>, CustomMemberRepository {
    fun findByNickname(nickname: String): MemberEntity?

    @Query("FROM MemberEntity e WHERE e.groupId = :groupId  ORDER BY e.nickname asc")
    fun getByGroupIdWithCursor(groupId: Long, pageable: Pageable): List<MemberEntity>

    @Query("FROM MemberEntity e WHERE e.groupId = :groupId AND e.nickname > :nickname ORDER BY e.nickname asc")
    fun getByGroupIdWithCursor(groupId: Long, nickname: String, pageable: Pageable): List<MemberEntity>
    fun findByNicknameAndGroupId(nickname: String, groupId: Long): MemberEntity?

    fun findByGroupIdAndUserId(groupId: Long, userId: Long): MemberEntity?
}
