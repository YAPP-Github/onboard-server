package com.yapp.bol.group.member

import com.yapp.bol.auth.UserId
import com.yapp.bol.base.BOOLEAN
import com.yapp.bol.base.ControllerTest
import com.yapp.bol.base.NUMBER
import com.yapp.bol.base.OpenApiTag
import com.yapp.bol.base.STRING
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.GroupService
import com.yapp.bol.group.member.dto.AddGuestRequest
import com.yapp.bol.group.member.dto.JoinGroupRequest
import io.mockk.every
import io.mockk.mockk

class MemberControllerTest : ControllerTest() {
    private val groupService: GroupService = mockk()
    private val memberService: MemberService = mockk()
    override val controller = MemberController(groupService, memberService)

    init {
        test("GET /v1/group/{groupId}/member/validateNickname") {
            every {
                memberService.validateMemberNickname(any(), any())
            } returns true

            get("/v1/group/1/member/validateNickname?groupId=1&nickname=holden") {}
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

        test("그룹 가입 - Host Member") {
            val groupId = GroupId(1)
            val userId = UserId(1)
            val request = JoinGroupRequest("nickname", "accessCode")

            every { groupService.joinGroup(any()) } returns Unit

            post("/v1/group/{groupId}/host", request, arrayOf(groupId.value)) {
                authorizationHeader(userId)
            }
                .isStatus(200)
                .makeDocument(
                    DocumentInfo(identifier = "member/{method-name}", tag = OpenApiTag.MEMBER),
                    pathParameters(
                        "groupId" type NUMBER means "그룹 ID",
                    ),
                    requestFields(
                        "nickname" type STRING means "그룹 전용 닉네임, null 일 경우 유저 기본 닉네임을 사용",
                        "accessCode" type STRING means "그룹에 가입하기 위한 참여 코드"
                    ),
                    responseFields()
                )
        }

        test("게스트 추가 - Host Member") {
            val groupId = GroupId(1)
            val userId = UserId(1)
            val request = AddGuestRequest("nickname")

            every { groupService.addGuest(any()) } returns Unit

            post("/v1/group/{groupId}/guest", request, arrayOf(groupId.value)) {
                authorizationHeader(userId)
            }
                .isStatus(200)
                .makeDocument(
                    DocumentInfo(identifier = "member/{method-name}", tag = OpenApiTag.MEMBER),
                    pathParameters(
                        "groupId" type NUMBER means "그룹 ID",
                    ),
                    requestFields(
                        "nickname" type STRING means "그룹 전용 닉네임, null 일 경우 유저 기본 닉네임을 사용" isOptional false,
                    ),
                    responseFields()
                )
        }
    }
}
