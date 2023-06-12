package com.yapp.bol.group.member

import com.yapp.bol.group.GroupQueryRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
internal class MemberQueryRepositoryImpl(
    private val memberRepository: MemberRepository
) : MemberQueryRepository {
    @Transactional(readOnly = true)
    override fun findByNicknameAndGroupId(nickname: String, groupId: Long): Member? {
        return memberRepository.findByNicknameAndGroupId(nickname, groupId)?.toDomain()
    }

    @Transactional(readOnly = true)
    override fun findByGroupId(groupId: Long): List<Member> {
        return memberRepository.findByGroupId(groupId).map { it.toDomain() }
    }
}
