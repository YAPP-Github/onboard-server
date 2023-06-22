package com.yapp.bol.group.member

import com.yapp.bol.EmptyResponse
import com.yapp.bol.auth.getSecurityUserIdOrThrow
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.GroupService
import com.yapp.bol.group.dto.AddGuestDto
import com.yapp.bol.group.dto.GetMembersByCursorDto
import com.yapp.bol.group.dto.JoinGroupDto
import com.yapp.bol.group.member.dto.AddGuestRequest
import com.yapp.bol.group.member.dto.JoinGroupRequest
import com.yapp.bol.group.member.dto.MemberResponse
import com.yapp.bol.group.member.dto.ValidateMemberNameResponse
import com.yapp.bol.group.member.dto.toResponse
import com.yapp.bol.pagination.SimpleCursorResponse
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/group/{groupId}")
class MemberController(
    private val groupService: GroupService,
    private val memberService: MemberService,
) {
    @GetMapping("/member/validateNickname")
    fun validateMemberName(
        @PathVariable groupId: GroupId,
        @RequestParam nickname: String,
    ): ValidateMemberNameResponse {
        return ValidateMemberNameResponse(memberService.validateMemberNickname(groupId, nickname))
    }

    @GetMapping("/member")
    fun getGroup(
        @PathVariable groupId: GroupId,
        @RequestParam size: Int,
        @RequestParam cursor: String?,
    ): SimpleCursorResponse<MemberResponse, String> {
        val request = GetMembersByCursorDto(groupId, size, cursor)
        val result = memberService.getMembers(request)

        return result.map { it.toResponse() }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/host")
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

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/guest")
    fun addGuestMember(
        @PathVariable groupId: GroupId,
        @RequestBody request: AddGuestRequest,
    ): EmptyResponse {
        val userId = getSecurityUserIdOrThrow()

        groupService.addGuest(
            AddGuestDto(
                groupId = groupId,
                requestUserId = userId,
                nickname = request.nickname,
            )
        )

        return EmptyResponse
    }
}
