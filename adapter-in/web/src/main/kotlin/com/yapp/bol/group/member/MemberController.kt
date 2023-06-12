package com.yapp.bol.group.member

import com.yapp.bol.group.member.dto.ValidateMemberNameResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/group/{groupId}/member")
class MemberController(
    private val memberService: MemberService,
) {
    @GetMapping("/validateNickname")
    fun validateMemberName(@PathVariable groupId: Long, @RequestParam nickname: String): ValidateMemberNameResponse {
        return ValidateMemberNameResponse(memberService.validateMemberNickname(groupId, nickname))
    }
}
