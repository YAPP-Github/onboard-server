package com.yapp.bol.group

import com.yapp.bol.group.dto.CreateGroupDto
import com.yapp.bol.group.member.MemberRole
import com.yapp.bol.group.member.MemberService
import com.yapp.bol.group.member.dto.CreateMemberDto
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

        val createMemberDto = CreateMemberDto(
            userId = createGroupDto.ownerId,
            groupId = groupPersisted.id,
            nickname = createGroupDto.nickname,
            role = MemberRole.OWNER,
        )

        val members = memberService.createMembers(listOf(createMemberDto))

        return GroupMemberList(
            group = groupPersisted,
            members = members,
        )
    }
}
