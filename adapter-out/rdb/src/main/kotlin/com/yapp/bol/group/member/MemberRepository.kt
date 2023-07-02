package com.yapp.bol.group.member

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

internal interface MemberRepository : JpaRepository<MemberEntity, Long>, CustomMemberRepository {
    fun findByNickname(nickname: String): MemberEntity?

    fun findByGroupId(groupId: Long): List<MemberEntity>

    fun findByNicknameAndGroupId(nickname: String, groupId: Long): MemberEntity?

    fun findByGroupIdAndUserId(groupId: Long, userId: Long): MemberEntity?

    @Query(
        "FROM MemberEntity m " +
            "LEFT JOIN FETCH m.gameMember " +
            "WHERE m.groupId = :groupId " +
            "AND m.gameMember.gameId = :gameId "
    )
    fun findWithGameMember(groupId: Long, gameId: Long): List<MemberEntity>
}
