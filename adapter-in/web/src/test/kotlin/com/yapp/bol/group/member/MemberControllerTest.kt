package com.yapp.bol.group.member

import com.yapp.bol.base.BOOLEAN
import com.yapp.bol.base.ControllerTest
import com.yapp.bol.base.NUMBER
import com.yapp.bol.base.OpenApiTag
import com.yapp.bol.base.STRING
import io.mockk.every
import io.mockk.mockk

class MemberControllerTest : ControllerTest() {
    private val memberService: MemberService = mockk()
    override val controller = MemberController(memberService)

    init {
        test("GET /v1/group/member/validate") {
            every {
                memberService.validateMemberNickname(any(), any())
            } returns true

            get("/v1/group/member/validateNickname?groupId=1&nickname=holden") {}
                .isStatus(200)
                .makeDocument(
                    DocumentInfo(identifier = "member", tag = OpenApiTag.MEMBER),
                    queryParameters(
                        "groupId" type NUMBER means "그룹 ID",
                        "nickname" type STRING means "닉네임"
                    ),
                    responseFields(
                        "isAvailable" type BOOLEAN means "그룹 내에서 닉네임 중복 여부"
                    )
                )
        }
    }
}
