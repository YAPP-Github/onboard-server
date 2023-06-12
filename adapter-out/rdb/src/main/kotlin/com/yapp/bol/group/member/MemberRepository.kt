package com.yapp.bol.group.member

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal interface MemberRepository : JpaRepository<MemberEntity, Long> {
    fun findByNickname(nickname: String): MemberEntity?
}
