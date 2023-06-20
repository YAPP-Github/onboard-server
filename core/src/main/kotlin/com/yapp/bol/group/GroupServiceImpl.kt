package com.yapp.bol.group

import com.yapp.bol.AccessCodeNotMatchException
import com.yapp.bol.NotFoundGroupException
import com.yapp.bol.group.dto.CreateGroupDto
import com.yapp.bol.group.dto.JoinGroupDto
import com.yapp.bol.group.member.MemberService
import com.yapp.bol.group.member.OwnerMember
import org.springframework.stereotype.Service

@Service
internal class GroupServiceImpl(
    private val groupQueryRepository: GroupQueryRepository,
    private val groupCommandRepository: GroupCommandRepository,
    private val memberService: MemberService,
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
}
