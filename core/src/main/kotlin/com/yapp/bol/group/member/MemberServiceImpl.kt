package com.yapp.bol.group.member

import com.yapp.bol.AlreadyExistMemberException
import com.yapp.bol.DuplicatedMemberNicknameException
import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.dto.PaginationCursorMemberRequest
import com.yapp.bol.pagination.cursor.SimplePaginationCursorResponse
import com.yapp.bol.validate.NicknameValidator
import org.springframework.stereotype.Service

@Service
internal class MemberServiceImpl(
    private val memberQueryRepository: MemberQueryRepository,
    private val memberCommandRepository: MemberCommandRepository,
) : MemberService {
    override fun validateMemberNickname(groupId: GroupId, nickname: String): Boolean =
        when {
            validateUniqueNickname(groupId, nickname).not() -> false
            NicknameValidator.validate(nickname).not() -> false
            else -> true
        }

    override fun createHostMember(userId: UserId, groupId: GroupId, nickname: String): HostMember {
        if (memberQueryRepository.findByGroupIdAndUserId(groupId, userId) != null) {
            throw AlreadyExistMemberException
        }

        if (validateUniqueNickname(groupId, nickname).not()) throw DuplicatedMemberNicknameException

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

    override fun getMembers(request: PaginationCursorMemberRequest): SimplePaginationCursorResponse<Member, String> =
        memberQueryRepository.getMemberListByCursor(request)

    override fun findMembersByGroupId(groupId: GroupId): List<Member> =
        memberQueryRepository.findByGroupId(groupId)

    private fun validateUniqueNickname(groupId: GroupId, nickname: String): Boolean =
        memberQueryRepository.findByNicknameAndGroupId(nickname, groupId) == null
}
