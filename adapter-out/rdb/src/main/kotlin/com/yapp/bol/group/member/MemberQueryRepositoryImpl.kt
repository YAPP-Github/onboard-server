package com.yapp.bol.group.member

import org.springframework.stereotype.Repository

@Repository
internal class MemberQueryRepositoryImpl(
    private val memberRepository: MemberRepository,
) : MemberQueryRepository {
    override fun findByNickname(nickname: String): Member? {
        return memberRepository.findByNickname(nickname)?.toDomain()
    }
}
