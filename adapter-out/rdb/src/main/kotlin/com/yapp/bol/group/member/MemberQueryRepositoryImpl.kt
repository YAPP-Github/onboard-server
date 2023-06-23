package com.yapp.bol.group.member

import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId
import com.yapp.bol.pagination.CursorRequest
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
internal class MemberQueryRepositoryImpl(
    private val memberRepository: MemberRepository,
) : MemberQueryRepository {

    @Transactional(readOnly = true)
    override fun findByNicknameAndGroupId(nickname: String, groupId: GroupId): Member? {
        return memberRepository.findByNicknameAndGroupId(nickname, groupId.value)?.toDomain()
    }

    override fun getMemberListByCursor(
        groupId: GroupId,
        nickname: String?,
        cursorRequest: CursorRequest<String>
    ): List<Member> {
        val entities = memberRepository.getByGroupIdWithCursor(groupId.value, nickname, cursorRequest)

        return entities.map { it.toDomain() }.toList()
    }

    @Transactional(readOnly = true)
    override fun findByGroupIdAndUserId(groupId: GroupId, userId: UserId): Member? {
        return memberRepository.findByGroupIdAndUserId(groupId.value, userId.value)?.toDomain()
    }
}
