package com.yapp.bol.user.dto

import com.yapp.bol.group.dto.GroupResponse

data class JoinedGroupResponse(
    val contents: List<GroupResponse>
)
