package com.yapp.bol.group.member

import com.yapp.bol.auth.UserId
import com.yapp.bol.base.ARRAY
import com.yapp.bol.base.BOOLEAN
import com.yapp.bol.base.ControllerTest
import com.yapp.bol.base.ENUM
import com.yapp.bol.base.NUMBER
import com.yapp.bol.base.OpenApiTag
import com.yapp.bol.base.STRING
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.GroupService
import com.yapp.bol.group.member.dto.AddGuestRequest
import com.yapp.bol.group.member.dto.JoinGroupRequest
import com.yapp.bol.pagination.cursor.SimpleCursorResponse
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

        test("맴버 목록 가져오기") {
            val groupId = GroupId(1)
            val userId = UserId(1)
            val size = 5
            val nickname = "검색"
            val cursor = "김김김"

            every { memberService.getMembers(any()) } returns SimpleCursorResponse(
                contents = List(size) {
                    HostMember(
                        id = MemberId(it.toLong()),
                        userId = UserId(it.toLong()),
                        nickname = "닉네임$it",
                        level = 0,
                    )
                },
                cursor = cursor,
                hasNext = true,
            )

            get("/v1/group/{groupId}/member", arrayOf(groupId.value)) {
                authorizationHeader(userId)
                queryParam("size", size.toString())
                queryParam("nickname", nickname)
                queryParam("cursor", cursor)
            }
                .isStatus(200)
                .makeDocument(
                    DocumentInfo(
                        identifier = "member/{method-name}",
                        tag = OpenApiTag.MEMBER,
                        description = "맴버 목록 가져오기"
                    ),
                    pathParameters(
                        "groupId" type NUMBER means "그룹 ID",
                    ),
                    queryParameters(
                        "size" type NUMBER means "받아오고자 하는 맴버 개수",
                        "cursor" type STRING means "Cursor 방식 Pagination, 전 요청 에서 받아온 cursor를 그대로 전달" isOptional true,
                        "nickname" type STRING means "검색하고자 하는 닉네임, null일 경우 전체 목록을 반환" isOptional true,
                    ),
                    responseFields(
                        "contents" type ARRAY means "맴버 목록",
                        "contents[].id" type NUMBER means "맴버 ID",
                        "contents[].role" type ENUM(MemberRole::class) means "맴버 종류 구분",
                        "contents[].nickname" type STRING means "맴버가 그룹에서 사용하는 닉네임",
                        "contents[].level" type NUMBER means "주사위 모양 데이터",
                        "cursor" type STRING means "다음 페이지를 가져오기 위한 기준 값",
                        "hasNext" type BOOLEAN means "다음 페이지 존재 여부",
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
