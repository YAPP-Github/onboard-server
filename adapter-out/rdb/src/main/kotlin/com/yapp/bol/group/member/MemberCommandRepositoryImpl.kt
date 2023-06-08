package com.yapp.bol.group.member

import org.springframework.stereotype.Repository

@Repository
internal class MemberCommandRepositoryImpl(
    private val memberRepository: MemberRepository,
) : MemberCommandRepository {
    override fun createMember(member: Member) {
        memberRepository.save(member.toEntity())
    }
}

fun Member.toEntity(): MemberEntity = MemberEntity(id, userId, role, nickname)
