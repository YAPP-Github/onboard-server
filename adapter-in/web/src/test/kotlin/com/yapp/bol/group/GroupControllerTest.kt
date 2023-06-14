package com.yapp.bol.group

import com.yapp.bol.base.ARRAY
import com.yapp.bol.base.BOOLEAN
import com.yapp.bol.base.ControllerTest
import com.yapp.bol.base.NUMBER
import com.yapp.bol.base.OBJECT
import com.yapp.bol.base.OpenApiTag
import com.yapp.bol.base.STRING
import com.yapp.bol.group.dto.CreateGroupRequest
import com.yapp.bol.group.member.Member
import com.yapp.bol.group.member.MemberList
import com.yapp.bol.group.member.MemberRole
import com.yapp.bol.pageable.ApplicationSlice
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

            post("/v1/group", request) {}
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

        test("GET /v1/group/search") {
            val name = "뽀글뽀글"
            val pageNumber = 0
            val pageSize = 10

            every {
                groupService.searchGroup(any(), any(), any())
            } returns ApplicationSlice<GroupWithMemberCount>(
                content = listOf(GROUP_WITH_MEMBER_COUNT),
                hasNext = false,
            )

            get("/v1/group/search?name=$name&pageNumber=$pageNumber&pageSize=$pageSize") {}
                .isStatus(200)
                .makeDocument(
                    DocumentInfo(identifier = "searchGroup", tag = OpenApiTag.GROUP),
                    queryParameters(
                        "name" type STRING means "그룹 이름",
                        "pageNumber" type NUMBER means "페이지 번호",
                        "pageSize" type NUMBER means "페이지 크기",
                    ),
                    responseFields(
                        "groups" type OBJECT means "그룹 목록",
                        "groups.content" type ARRAY means "그룹 목록",
                        "groups.content[].id" type NUMBER means "그룹 ID",
                        "groups.content[].name" type STRING means "그룹 이름",
                        "groups.content[].description" type STRING means "그룹 설명",
                        "groups.content[].organization" type STRING means "그룹 소속",
                        "groups.content[].profileImageUrl" type STRING means "그룹 프로필 이미지 URL",
                        "groups.content[].memberCount" type NUMBER means "그룹 멤버 수",
                        "groups.hasNext" type BOOLEAN means "다음 페이지 존재 여부",
                    )
                )
        }
    }

    companion object {
        val GROUP = Group(
            id = 1,
            name = "뽀글뽀글",
            description = "보겜동입니다",
            organization = "카카오",
            profileImageUrl = "https://profile.com",
            accessCode = "1A2B3C",
        )

        val MEMBER_LIST = MemberList.of(
            Member(
                id = 123,
                nickname = "홀든",
                groupId = GROUP.id,
                role = MemberRole.OWNER
            )
        )

        val GROUP_WITH_MEMBER_COUNT = GroupWithMemberCount(
            id = GROUP.id,
            name = GROUP.name,
            description = GROUP.description,
            organization = GROUP.organization,
            profileImageUrl = GROUP.profileImageUrl,
            memberCount = MEMBER_LIST.members.size
        )
    }
}