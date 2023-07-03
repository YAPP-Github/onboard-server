package com.yapp.bol.group

import com.yapp.bol.auth.getSecurityUserIdOrThrow
import com.yapp.bol.game.GameId
import com.yapp.bol.file.FileService
import com.yapp.bol.file.dto.FileResponse
import com.yapp.bol.group.dto.CreateGroupRequest
import com.yapp.bol.group.dto.CreateGroupResponse
import com.yapp.bol.group.dto.GroupWithMemberCount
import com.yapp.bol.group.dto.LeaderBoardResponse
import com.yapp.bol.group.dto.toCreateGroupResponse
import com.yapp.bol.group.dto.toDto
import com.yapp.bol.group.dto.toResponse
import com.yapp.bol.pagination.offset.PaginationOffsetResponse
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/group")
class GroupController(
    private val groupService: GroupService,
    private val fileService: FileService,
) {
    @GetMapping("/default-image")
    fun getDefaultImage(): FileResponse = FileResponse(url = fileService.getDefaultGroupImageUrl())

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

    @GetMapping("/{groupId}/game/{gameId}")
    fun getLeaderboard(
        @PathVariable groupId: GroupId,
        @PathVariable gameId: GameId,
    ): LeaderBoardResponse {
        val list = groupService.getLeaderBoard(groupId, gameId).mapIndexed { index, it -> it.toResponse(index + 1) }
        return LeaderBoardResponse(list)
    }
}
