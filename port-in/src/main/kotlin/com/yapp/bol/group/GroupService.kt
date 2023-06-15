package com.yapp.bol.group

import com.yapp.bol.group.dto.CreateGroupDto
import com.yapp.bol.group.dto.GroupMemberList
import com.yapp.bol.group.dto.GroupWithMemberCount
import com.yapp.bol.pageable.ApplicationSlice

interface GroupService {
    fun createGroup(
        createGroupDto: CreateGroupDto
    ): GroupMemberList

    fun searchGroup(
        name: String,
        pageNumber: Int,
        pageSize: Int
    ): ApplicationSlice<GroupWithMemberCount>
}
