package com.yapp.bol.group.member

import com.yapp.bol.group.GroupQueryRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
internal class MemberQueryRepositoryImpl(
    private val groupQueryRepository: GroupQueryRepository
) : MemberQueryRepository {
    @Transactional(readOnly = true)
    override fun findByNickname(groupId: Long, nickname: String): Member? {
        val group = groupQueryRepository.findById(groupId) ?: throw IllegalArgumentException("그룹이 존재하지 않습니다.")

        return group.members.findMemberByNickname(nickname)?.let {
            return it
        }
    }
}
