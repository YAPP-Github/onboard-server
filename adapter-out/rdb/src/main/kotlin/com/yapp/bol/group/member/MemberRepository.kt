package com.yapp.bol.group.member

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal interface MemberRepository : JpaRepository<MemberEntity, Long> {
    fun findByNickname(nickname: String): MemberEntity?
}

fun MemberEntity.toDomain(): Member = Member(
    id = this.id,
    userId = this.userId,
    role = this.role,
    nickname = this.nickname,
)

fun Member.toEntity(): MemberEntity = MemberEntity(
    id = this.id,
    userId = this.userId,
    role = this.role,
    nickname = this.nickname,
)
