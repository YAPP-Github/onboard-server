package com.yapp.bol.group.member

import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId
import com.yapp.bol.pagination.SimpleCursorResponse
import com.yapp.bol.pagination.group.member.MemberCursorRequest
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
        request: MemberCursorRequest
    ): SimpleCursorResponse<Member, String> {
        val originalSize = request.size

        val extraMembers = request.copy(size = originalSize + 1)
            .let { extraRequest ->
                memberRepository.getByGroupIdWithCursor(
                    groupId = extraRequest.groupId.value,
                    nickname = extraRequest.nickname,
                    cursor = extraRequest
                ).map { it.toDomain() }
            }

        val hasNext = extraMembers.size > originalSize
        val contents = extraMembers.take(originalSize)

        return SimpleCursorResponse(
            contents = contents,
            cursor = contents.last().nickname,
            hasNext = hasNext,
        )
    }

    @Transactional(readOnly = true)
    override fun findByGroupId(groupId: GroupId): List<Member> {
        return memberRepository.findByGroupId(groupId.value).map { it.toDomain() }
    }

    @Transactional(readOnly = true)
    override fun findByGroupIdAndUserId(groupId: GroupId, userId: UserId): Member? {
        return memberRepository.findByGroupIdAndUserId(groupId.value, userId.value)?.toDomain()
    }
}
