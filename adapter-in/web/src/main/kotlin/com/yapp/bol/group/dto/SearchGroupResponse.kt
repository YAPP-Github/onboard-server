package com.yapp.bol.group.dto

import com.yapp.bol.group.GroupWithMemberCount
import com.yapp.bol.pageable.ApplicationSlice

data class SearchGroupResponse(
    val groups: ApplicationSlice<GroupWithMemberCount>,
)
