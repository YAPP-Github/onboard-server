package com.yapp.bol.group

import com.yapp.bol.group.dto.CreateGroupDto
import com.yapp.bol.group.member.Member
import org.springframework.stereotype.Service

@Service
internal class GroupServiceImpl(
    private val groupCommandRepository: GroupCommandRepository,
) : GroupService {

    override fun createGroup(
        dto: CreateGroupDto
    ): Group {
        val group = Group.of(
            name = dto.name,
            description = dto.description,
            organization = dto.organization,
            profileImageUrl = dto.profileImageUrl ?: Group.DEFAULT_PROFILE_IMAGE_URL,
            owner = Member.createOwner(dto.ownerId, dto.nickname)
        )

        return groupCommandRepository.createGroup(group)
    }
}
