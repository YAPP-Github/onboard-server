package com.yapp.bol.group.member

import org.springframework.stereotype.Repository

@Repository
internal class MemberCommandRepositoryImpl(
    private val memberRepository: MemberRepository,
) : MemberCommandRepository {
    override fun createMember(member: Member): Member {
        return memberRepository.save(member.toEntity()).toDomain()
    }

    override fun createMembers(members: MemberList): MemberList {
        val memberEntities = memberRepository.saveAll(members.toList().map(Member::toEntity))

        return memberEntities
            .map(MemberEntity::toDomain)
            .let { MemberList(it.toMutableList()) }
    }
}
