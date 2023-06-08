package com.yapp.bol.group.member

import org.springframework.stereotype.Service

@Service
internal class MemberServiceImpl(
    private val memberQueryRepository: MemberQueryRepository,
) : MemberService {
    override fun validateMemberName(nickname: String): Boolean {
        return memberQueryRepository.findByNickname(nickname) == null
    }
}
