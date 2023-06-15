package com.yapp.bol.group.member

import com.yapp.bol.group.GroupId
import org.springframework.data.jpa.repository.JpaRepository

internal interface MemberRepository : JpaRepository<MemberEntity, Long> {
    fun findByNickname(nickname: String): MemberEntity?

    fun findByGroupId(groupId: GroupId): List<MemberEntity>

    fun findByNicknameAndGroupId(nickname: String, groupId: GroupId): MemberEntity?
}
