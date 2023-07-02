package com.yapp.bol.group

import com.yapp.bol.auth.getSecurityUserIdOrThrow
import com.yapp.bol.group.dto.CreateGroupRequest
import com.yapp.bol.group.dto.CreateGroupResponse
import com.yapp.bol.group.dto.GroupWithMemberCount
import com.yapp.bol.group.dto.toCreateGroupResponse
import com.yapp.bol.group.dto.toDto
import com.yapp.bol.pagination.offset.PaginationOffsetResponse
import org.springframework.security.access.prepost.PreAuthorize
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
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    fun createGroup(@RequestBody request: CreateGroupRequest): CreateGroupResponse {
        val userId = getSecurityUserIdOrThrow()

        return groupService.createGroup(request.toDto(userId)).toCreateGroupResponse()
    }

    @GetMapping
    fun searchGroup(
        @RequestParam keyword: String?,
        @RequestParam(defaultValue = "0") pageNumber: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): PaginationOffsetResponse<GroupWithMemberCount> =
        groupService.searchGroup(
            keyword = keyword,
            pageNumber = pageNumber,
            pageSize = pageSize,
        )
}
