package com.yapp.bol.group

import com.yapp.bol.group.dto.CreateGroupDto
import com.yapp.bol.group.dto.JoinGroupDto

interface GroupService {
    fun createGroup(
        createGroupDto: CreateGroupDto
    ): GroupMemberList

    fun joinGroup(request: JoinGroupDto)
}
