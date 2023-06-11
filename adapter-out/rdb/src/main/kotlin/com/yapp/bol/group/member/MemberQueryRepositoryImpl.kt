package com.yapp.bol.group.member

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
internal class MemberQueryRepositoryImpl(
    private val memberRepository: MemberRepository
) : MemberQueryRepository {
    @Transactional(readOnly = true)
    override fun findByNicknameAndGroupId(groupId: Long, nickname: String): Member? {
        return memberRepository.findByNicknameAndGroupId(nickname, groupId)?.toDomain()
    }

    @Transactional(readOnly = true)
    override fun findByGroupId(groupId: Long): List<Member> {
        return memberRepository.findByGroupId(groupId).map { it.toDomain() }
    }
}
