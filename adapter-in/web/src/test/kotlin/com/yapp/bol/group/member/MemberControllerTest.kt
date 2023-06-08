package com.yapp.bol.group.member

import com.yapp.bol.base.BOOLEAN
import com.yapp.bol.base.ControllerTest
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
                memberService.validateMemberName(any())
            } returns true

            get("/v1/group/member/validate?nickname=holden") {}
                .isStatus(200)
                .makeDocument(
                    DocumentInfo(identifier = "test", tag = OpenApiTag.MEMBER),
                    queryParameters(
                        "nickname" type STRING means "닉네임"
                    ),
                    responseFields(
                        "isAvailable" type BOOLEAN means "닉네임 중복 여부"
                    )
                )
        }
    }
}
