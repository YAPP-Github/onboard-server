package com.yapp.bol.group.member

import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.dto.CreateMemberDto
import org.springframework.stereotype.Service

@Service
internal class MemberServiceImpl(
    private val memberQueryRepository: MemberQueryRepository,
    private val memberCommandRepository: MemberCommandRepository
) : MemberService {
    override fun validateMemberNickname(groupId: GroupId, nickname: String): Boolean {
        return memberQueryRepository.findByNicknameAndGroupId(nickname, groupId) == null
    }

    override fun createMembers(createMemberDtos: List<CreateMemberDto>): MemberList {
        val members = createMemberDtos.map {
            OwnerMember(
                userId = it.userId ?: UserId(0),
                groupId = it.groupId,
                nickname = it.nickname,
            )
        }.let {
            MemberList(it.get(0))
        }

        return memberCommandRepository.createMembers(members)
    }
}
