package com.yapp.bol.group.member

import org.springframework.data.jpa.repository.JpaRepository

internal interface MemberRepository : JpaRepository<MemberEntity, Long> {
    fun findByNickname(nickname: String): MemberEntity?

    fun findByGroupId(groupId: Long): List<MemberEntity>

    fun findByNicknameAndGroupId(nickname: String, groupId: Long): MemberEntity?
}
