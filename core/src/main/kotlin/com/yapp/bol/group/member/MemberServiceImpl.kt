package com.yapp.bol.group.member

import com.yapp.bol.DuplicatedMemberNicknameException
import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId
import org.springframework.stereotype.Service

@Service
internal class MemberServiceImpl(
    private val memberQueryRepository: MemberQueryRepository,
    private val memberCommandRepository: MemberCommandRepository
) : MemberService {
    override fun validateMemberNickname(groupId: GroupId, nickname: String): Boolean {
        return memberQueryRepository.findByNicknameAndGroupId(nickname, groupId) == null
    }

    override fun createMember(userId: UserId?, groupId: GroupId, nickname: String?, isOwner: Boolean): Member {
        val nicknameResult = nickname ?: "기본 닉네임" // TODO: UserEntity 에서 조회

        if (validateMemberNickname(groupId, nicknameResult).not()) throw DuplicatedMemberNicknameException

        val member = createMemberDomain(userId, nicknameResult, isOwner)
        return memberCommandRepository.createMember(groupId, member)
    }

    private fun createMemberDomain(userId: UserId?, nickname: String, isOwner: Boolean): Member =
        if (userId == null) {
            GuestMember(
                nickname = nickname,
            )
        } else if (isOwner) {
            OwnerMember(
                userId = userId,
                nickname = nickname,
            )
        } else {
            HostMember(
                userId = userId,
                nickname = nickname,
            )
        }
}
