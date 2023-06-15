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

    override fun createMember(userId: UserId?, groupId: GroupId, inputNickname: String?, isOwner: Boolean): Member {
        val nickname = inputNickname ?: "기본 닉네임" // TODO: UserEntity 에서 조회

        if (validateMemberNickname(groupId, nickname).not()) throw DuplicatedMemberNicknameException

        val member = createMemberDomain(userId, groupId, nickname, isOwner)
        return memberCommandRepository.createMember(member)
    }

    private fun createMemberDomain(userId: UserId?, groupId: GroupId, nickname: String, isOwner: Boolean): Member =
        if (userId == null) {
            GuestMember(
                groupId = groupId,
                nickname = nickname,
            )
        } else if (isOwner) {
            OwnerMember(
                userId = userId,
                groupId = groupId,
                nickname = nickname,
            )
        } else {
            HostMember(
                userId = userId,
                groupId = groupId,
                nickname = nickname,
            )
        }
}
