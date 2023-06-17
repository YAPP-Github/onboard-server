package com.yapp.bol.group.member

import com.yapp.bol.AlreadyExistMemberException
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

    override fun createHostMember(userId: UserId, groupId: GroupId, nickname: String): HostMember {
        if (memberQueryRepository.findByGroupIdAndUserId(groupId, userId) != null) {
            throw AlreadyExistMemberException
        }

        if (validateMemberNickname(groupId, nickname).not()) throw DuplicatedMemberNicknameException

        val member = HostMember(
            userId = userId,
            nickname = nickname
        )

        return memberCommandRepository.createMember(groupId, member) as HostMember
    }

    override fun createGuestMember(groupId: GroupId, nickname: String): GuestMember {
        val member = GuestMember(nickname = nickname)

        return memberCommandRepository.createMember(groupId, member) as GuestMember
    }
}
