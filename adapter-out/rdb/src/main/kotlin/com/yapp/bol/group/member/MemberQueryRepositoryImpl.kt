package com.yapp.bol.group.member

import com.yapp.bol.group.GroupId
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
internal class MemberQueryRepositoryImpl(
    private val memberRepository: MemberRepository
) : MemberQueryRepository {
    @Transactional(readOnly = true)
    override fun findByNicknameAndGroupId(nickname: String, groupId: GroupId): Member? {
        return memberRepository.findByNicknameAndGroupId(nickname, groupId.value)?.toDomain()
    }

    @Transactional(readOnly = true)
    override fun findByGroupId(groupId: GroupId): List<Member> {
        return memberRepository.findByGroupId(groupId.value).map { it.toDomain() }
    }
}
