package com.yapp.bol.group.member

import org.springframework.data.jpa.repository.JpaRepository

internal interface MemberRepository : JpaRepository<MemberEntity, Long>, CustomMemberRepository {
    fun findByNickname(nickname: String): MemberEntity?

    fun findByNicknameAndGroupId(nickname: String, groupId: Long): MemberEntity?

    fun findByGroupIdAndUserId(groupId: Long, userId: Long): MemberEntity?
}
