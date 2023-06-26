package com.yapp.bol.group

import com.yapp.bol.AccessCodeNotMatchException
import com.yapp.bol.NotFoundGroupException
import com.yapp.bol.UnAuthorizationException
import com.yapp.bol.auth.UserId
import com.yapp.bol.group.dto.AddGuestDto
import com.yapp.bol.group.dto.CreateGroupDto
import com.yapp.bol.group.dto.GroupMemberList
import com.yapp.bol.group.dto.GroupWithMemberCount
import com.yapp.bol.group.dto.JoinGroupDto
import com.yapp.bol.group.member.MemberQueryRepository
import com.yapp.bol.group.member.MemberService
import com.yapp.bol.group.member.OwnerMember
import com.yapp.bol.pagination.offset.PaginationOffsetResponse
import org.springframework.stereotype.Service

@Service
internal class GroupServiceImpl(
    private val groupQueryRepository: GroupQueryRepository,
    private val groupCommandRepository: GroupCommandRepository,
    private val memberService: MemberService,
    private val memberQueryRepository: MemberQueryRepository,
) : GroupService {

    override fun createGroup(
        createGroupDto: CreateGroupDto
    ): GroupMemberList {
        val group = Group(
            name = createGroupDto.name,
            description = createGroupDto.description,
            organization = createGroupDto.organization,
            profileImageUrl = createGroupDto.profileImageUrl ?: Group.DEFAULT_PROFILE_IMAGE_URL,
        )

        val owner = OwnerMember(
            userId = createGroupDto.ownerId,
            nickname = createGroupDto.nickname ?: "기본 닉네임" // TODO: User Service
        )

        return groupCommandRepository.createGroup(group, owner)
    }

    override fun joinGroup(request: JoinGroupDto) {
        val group = groupQueryRepository.findById(request.groupId) ?: throw NotFoundGroupException

        if (group.accessCode != request.accessCode) throw AccessCodeNotMatchException

        memberService.createHostMember(request.userId, request.groupId, request.nickname)
    }

    override fun searchGroup(
        name: String?,
        pageNumber: Int,
        pageSize: Int
    ): PaginationOffsetResponse<GroupWithMemberCount> {
        val groups = groupQueryRepository.findByNameLike(
            name = name,
            pageNumber = pageNumber,
            pageSize = pageSize
        )

        val groupWithMemberCount = groups.content.map { group ->
            val members = memberService.findMembersByGroupId(group.id)

            GroupWithMemberCount.of(group, members)
        }

        return PaginationOffsetResponse(groupWithMemberCount, groups.hasNext)
    }

    override fun addGuest(request: AddGuestDto) {
        memberQueryRepository.findByGroupIdAndUserId(request.groupId, request.requestUserId)
            ?: throw UnAuthorizationException()

        memberService.createGuestMember(request.groupId, request.nickname)
    }

    override fun getGroupsByUserId(userId: UserId): List<Group> {
        return groupQueryRepository.getGroupsByUserId(userId)
    }
}
