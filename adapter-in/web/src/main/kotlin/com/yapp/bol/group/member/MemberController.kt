package com.yapp.bol.group.member

import com.yapp.bol.EmptyResponse
import com.yapp.bol.auth.getSecurityUserIdOrThrow
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.GroupService
import com.yapp.bol.group.dto.JoinGroupDto
import com.yapp.bol.group.member.dto.JoinGroupRequest
import com.yapp.bol.group.member.dto.ValidateMemberNameResponse
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/group/{groupId}/member")
class MemberController(
    private val groupService: GroupService,
    private val memberService: MemberService,
) {
    @GetMapping("/validateNickname")
    fun validateMemberName(
        @PathVariable groupId: GroupId,
        @RequestParam nickname: String,
    ): ValidateMemberNameResponse {
        return ValidateMemberNameResponse(memberService.validateMemberNickname(groupId, nickname))
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/join")
    fun joinHostMember(
        @PathVariable groupId: GroupId,
        @RequestBody request: JoinGroupRequest,
    ): EmptyResponse {
        val userId = getSecurityUserIdOrThrow()

        groupService.joinGroup(
            JoinGroupDto(
                groupId = groupId,
                userId = userId,
                nickname = request.nickname,
                accessCode = request.accessCode,
            )
        )

        return EmptyResponse
    }
}
