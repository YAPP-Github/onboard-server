package com.yapp.bol.group.member

import com.yapp.bol.NotFoundMemberException
import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
internal class MemberCommandRepositoryImpl(
    private val memberRepository: MemberRepository,
) : MemberCommandRepository {
    override fun createMember(groupId: GroupId, member: Member): Member {
        return memberRepository.save(member.toEntity(groupId.value)).toDomain()
    }

    override fun updateGuestToHost(groupId: GroupId, memberId: MemberId, userId: UserId) {
        val member = memberRepository.findByIdOrNull(memberId.value) ?: throw NotFoundMemberException
        if (member.userId != null || member.groupId != groupId.value) throw NotFoundMemberException

        member.toHost(userId.value)
        memberRepository.save(member)
    }
}
