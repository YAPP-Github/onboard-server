package com.yapp.bol.group

import com.yapp.bol.group.dto.CreateGroupDto
import com.yapp.bol.group.member.Member
import org.springframework.stereotype.Service

@Service
internal class GroupServiceImpl(
    private val groupCommandRepository: GroupCommandRepository,
) : GroupService {

    override fun createGroup(
        createGroupDto: CreateGroupDto
    ): Group {
        val group = Group.of(
            name = createGroupDto.name,
            description = createGroupDto.description,
            organization = createGroupDto.organization,
            profileImageUrl = createGroupDto.profileImageUrl ?: Group.DEFAULT_PROFILE_IMAGE_URL,
            owner = Member.createOwner(createGroupDto.ownerId, createGroupDto.nickname)
        )

        return groupCommandRepository.createGroup(group)
    }
}
