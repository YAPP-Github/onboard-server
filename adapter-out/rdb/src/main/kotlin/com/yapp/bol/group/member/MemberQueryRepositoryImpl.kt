package com.yapp.bol.group.member

import com.yapp.bol.MultiOwnerException
import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.dto.PaginationCursorMemberRequest
import com.yapp.bol.pagination.cursor.SimplePaginationCursorResponse
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
        request: PaginationCursorMemberRequest
    ): SimplePaginationCursorResponse<Member, String> {
        val originalSize = request.size

        val extraMembers = getExtraMembers(request).map { it.toDomain() }

        val hasNext = extraMembers.size > originalSize
        val contents = extraMembers.take(originalSize)

        return SimplePaginationCursorResponse(
            contents = contents,
            cursor = contents.lastOrNull()?.nickname ?: "",
            hasNext = hasNext,
        )
    }

    private fun getExtraMembers(request: PaginationCursorMemberRequest): List<MemberEntity> {
        val extraRequest = request.copy(size = request.size + 1)

        return memberRepository.getByGroupIdWithCursor(
            groupId = extraRequest.groupId.value,
            nickname = extraRequest.nickname,
            cursor = extraRequest
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

    @Transactional(readOnly = true)
    override fun findOwner(groupId: GroupId): OwnerMember {
        val list = memberRepository.findByGroupIdAndRole(groupId.value, MemberRole.OWNER)

        if (list.isEmpty()) throw MultiOwnerException

        return list.first().toDomain() as OwnerMember
    }

    @Transactional(readOnly = true)
    override fun getCount(groupId: GroupId): Int {
        return memberRepository.countByGroupId(groupId.value).toInt()
    }
}
