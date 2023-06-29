package com.yapp.bol.group

import com.yapp.bol.auth.UserId
import com.yapp.bol.base.ARRAY
import com.yapp.bol.base.BOOLEAN
import com.yapp.bol.base.ControllerTest
import com.yapp.bol.base.NUMBER
import com.yapp.bol.base.OpenApiTag
import com.yapp.bol.base.STRING
import com.yapp.bol.game.GameId
import com.yapp.bol.group.dto.CreateGroupRequest
import com.yapp.bol.group.dto.GroupMemberList
import com.yapp.bol.group.dto.GroupWithMemberCount
import com.yapp.bol.group.member.MemberList
import com.yapp.bol.group.member.OwnerMember
import com.yapp.bol.pagination.offset.PaginationOffsetResponse
import io.mockk.every
import io.mockk.mockk

class GroupControllerTest : ControllerTest() {
    private val groupService: GroupService = mockk()
    override val controller = GroupController(groupService)

    init {
        test("POST /v1/group") {
            val request = CreateGroupRequest(
                name = "뽀글뽀글",
                description = "보겜동입니다",
                organization = "카카오",
                profileImageUrl = "https://profile.com",
                nickname = "홀든",
            )

            every {
                groupService.createGroup(any())
            } returns GroupMemberList(group = GROUP, members = MEMBER_LIST)

            post("/v1/group", request) {
                authorizationHeader(UserId(1))
            }
                .isStatus(200)
                .makeDocument(
                    DocumentInfo(identifier = "group", tag = OpenApiTag.GROUP),
                    requestFields(
                        "name" type STRING means "그룹 이름",
                        "description" type STRING means "그룹 설명",
                        "organization" type STRING means "그룹 소속",
                        "profileImageUrl" type STRING means "그룹 프로필 이미지 URL" isOptional true,
                        "nickname" type STRING means "그룹장 닉네임" isOptional true,
                    ),
                    responseFields(
                        "id" type NUMBER means "그룹 ID",
                        "name" type STRING means "그룹 이름",
                        "description" type STRING means "그룹 설명",
                        "owner" type STRING means "그룹장 닉네임",
                        "organization" type STRING means "그룹 소속",
                        "profileImageUrl" type STRING means "그룹 프로필 이미지 URL",
                        "accessCode" type STRING means "그룹 접근 코드"
                    )
                )
        }

        test("GET /v1/group") {
            val name = "뽀글뽀글"
            val pageNumber = 0
            val pageSize = 10

            every {
                groupService.searchGroup(any(), any(), any())
            } returns PaginationOffsetResponse<GroupWithMemberCount>(
                content = listOf(GROUP_WITH_MEMBER_COUNT),
                hasNext = false,
            )

            get("/v1/group?name=$name&pageNumber=$pageNumber&pageSize=$pageSize") {}
                .isStatus(200)
                .makeDocument(
                    DocumentInfo(identifier = "group", tag = OpenApiTag.GROUP),
                    queryParameters(
                        "name" type STRING means "그룹 이름 (디폴트 전체)" isOptional true,
                        "pageNumber" type NUMBER means "페이지 번호 (디폴트 0)" isOptional true,
                        "pageSize" type NUMBER means "페이지 크기 (디폴트 10)" isOptional true
                    ),
                    responseFields(
                        "content" type ARRAY means "그룹 목록",
                        "content[].id" type NUMBER means "그룹 ID",
                        "content[].name" type STRING means "그룹 이름",
                        "content[].description" type STRING means "그룹 설명",
                        "content[].organization" type STRING means "그룹 소속" isOptional true,
                        "content[].profileImageUrl" type STRING means "그룹 프로필 이미지 URL",
                        "content[].memberCount" type NUMBER means "그룹 멤버 수",
                        "hasNext" type BOOLEAN means "다음 페이지 존재 여부"
                    )
                )
        }

        test("리더보드 보기") {
            val groupId = GroupId(1)
            val gameId = GameId(123)

            get("/v1/group/{groupId}/game/{gameId}", arrayOf(groupId.value, gameId.value)) {}
                .isStatus(200)
                .makeDocument(
                    DocumentInfo(
                        identifier = "group/{method-name}",
                        description = "게임 별 리더보드 보기",
                        tag = OpenApiTag.GROUP
                    ),
                    pathParameters(
                        "groupId" type NUMBER means "그룹 ID",
                        "gameId" type NUMBER means "게임 ID",
                    ),
                    responseFields(
                        "contents" type ARRAY means "그룹 목록",
                        "contents[].id" type NUMBER means "맴버 ID",
                        "contents[].rank" type NUMBER means "등수, 1부터 시작",
                        "contents[].name" type STRING means "맴버 이름",
                        "contents[].winningPercentage" type NUMBER means "승률 소수로 나옴 (반올림 없음), 0~1",
                        "contents[].playCount" type NUMBER means "총 플레이 횟수",
                    )
                )
        }
    }

    companion object {
        val GROUP = Group(
            id = GroupId(1),
            name = "뽀글뽀글",
            description = "보겜동입니다",
            organization = "카카오",
            profileImageUrl = "https://profile.com",
            accessCode = "1A2B3C",
        )

        val MEMBER_LIST = MemberList(
            OwnerMember(
                userId = UserId(1),
                nickname = "홀든",
            )
        )

        val GROUP_WITH_MEMBER_COUNT = GroupWithMemberCount(
            id = GROUP.id,
            name = GROUP.name,
            description = GROUP.description,
            organization = GROUP.organization ?: "",
            profileImageUrl = GROUP.profileImageUrl,
            memberCount = MEMBER_LIST.getSize()
        )
    }
}
