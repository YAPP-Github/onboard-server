package com.yapp.bol.group

import com.yapp.bol.group.dto.CreateGroupDto

interface GroupService {
    fun createGroup(
        createGroupDto: CreateGroupDto
    ): GroupMemberList
}
