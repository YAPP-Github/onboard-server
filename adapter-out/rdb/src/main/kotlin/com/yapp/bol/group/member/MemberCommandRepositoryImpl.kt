package com.yapp.bol.group.member

import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId
import org.springframework.stereotype.Repository

@Repository
internal class MemberCommandRepositoryImpl(
    private val memberRepository: MemberRepository,
) : MemberCommandRepository {
    override fun createMember(groupId: GroupId, member: Member): Member {
        return memberRepository.save(member.toEntity(groupId.value)).toDomain()
    }

    override fun unregisterUser(userId: UserId) {
        val list = memberRepository.findByUserId(userId.value)

        list.forEach {
            it.delete()
        }

        memberRepository.saveAll(list)
    }
}
