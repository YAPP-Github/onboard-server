package com.yapp.bol.group.member

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
            Member(
                userId = it.userId,
                groupId = it.groupId,
                nickname = it.nickname,
                role = it.role,
            )
        }.let {
            MemberList(it.toMutableList())
        }

        return memberCommandRepository.createMembers(members)
    }
}
