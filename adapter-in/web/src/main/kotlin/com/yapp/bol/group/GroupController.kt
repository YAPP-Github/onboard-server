package com.yapp.bol.group

import com.yapp.bol.group.dto.CreateGroupRequest
import com.yapp.bol.group.dto.CreateGroupResponse
import com.yapp.bol.group.dto.toDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/group")
class GroupController(
    private val groupService: GroupService,
) {
    @PostMapping()
    fun createGroup(@RequestBody request: CreateGroupRequest): CreateGroupResponse {
        return groupService.createGroup(request.toDto(1)).toDto() // FIXME: 시큐리티 적용 필요
    }
}
