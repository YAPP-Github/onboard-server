package com.yapp.bol.group

import com.yapp.bol.group.dto.CreateGroupDto
import com.yapp.bol.group.member.MemberList
import com.yapp.bol.group.member.MemberService
import com.yapp.bol.group.member.OwnerMember
import org.springframework.stereotype.Service

@Service
internal class GroupServiceImpl(
    private val groupCommandRepository: GroupCommandRepository,
    private val memberService: MemberService,
) : GroupService {

    override fun createGroup(
        createGroupDto: CreateGroupDto
    ): GroupMemberList {
        val group = Group.of(
            name = createGroupDto.name,
            description = createGroupDto.description,
            organization = createGroupDto.organization,
            profileImageUrl = createGroupDto.profileImageUrl ?: Group.DEFAULT_PROFILE_IMAGE_URL,
        )

        val groupPersisted = groupCommandRepository.createGroup(group)

        val owner = memberService.createMember(
            userId = createGroupDto.ownerId,
            nickname = createGroupDto.nickname,
            groupId = groupPersisted.id,
            isOwner = true,
        ) as OwnerMember

        return GroupMemberList(
            group = groupPersisted,
            members = MemberList(owner),
        )
    }
}
