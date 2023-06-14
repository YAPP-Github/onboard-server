package com.yapp.bol.group

import com.yapp.bol.group.dto.CreateGroupRequest
import com.yapp.bol.group.dto.CreateGroupResponse
import com.yapp.bol.group.dto.GroupWithMemberCount
import com.yapp.bol.group.dto.toCreateGroupResponse
import com.yapp.bol.group.dto.toDto
import com.yapp.bol.pageable.PaginationCursor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/group")
class GroupController(
    private val groupService: GroupService,
) {
    @PostMapping
    fun createGroup(@RequestBody request: CreateGroupRequest): CreateGroupResponse {
        return groupService.createGroup(request.toDto(1)).toCreateGroupResponse() // FIXME: 시큐리티 적용 필요
    }

    @GetMapping("/search")
    fun searchGroup(
        @RequestParam name: String,
        @RequestParam pageNumber: Int,
        @RequestParam pageSize: Int
    ): PaginationCursor<GroupWithMemberCount> =
        groupService.searchGroup(
            name = name,
            pageNumber = pageNumber,
            pageSize = pageSize,
        )

        return SearchGroupResponse(groups = groupWithMemberCount)
    }
}
