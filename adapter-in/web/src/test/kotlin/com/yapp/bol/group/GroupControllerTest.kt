package com.yapp.bol.group

import com.yapp.bol.base.ControllerTest
import com.yapp.bol.base.NUMBER
import com.yapp.bol.base.OpenApiTag
import com.yapp.bol.base.STRING
import com.yapp.bol.group.dto.CreateGroupRequest
import com.yapp.bol.group.member.Member
import com.yapp.bol.group.member.MemberList
import com.yapp.bol.group.member.MemberRole
import io.mockk.every
import io.mockk.mockk

class GroupControllerTest : ControllerTest() {
    private val groupService: GroupService = mockk()
    override val controller = GroupController(groupService)

    init {
        test("POST /v1/group") {
            val request = CreateGroupRequest("name", "description", "organization", "profileImageUrl", 123, "nick")

            val group = Group(
                id = 1,
                name = "name",
                description = "description",
                organization = "organization",
                profileImageUrl = "profileImageUrl",
                accessCode = "1A2B3C",
                members = MemberList.of(
                    Member(
                        id = 123,
                        nickname = "nick",
                        role = MemberRole.OWNER
                    )
                )
            )

            every {
                groupService.createGroup(any())
            } returns group

            post("/v1/group", request) {}
                .isStatus(200)
                .makeDocument(
                    DocumentInfo(identifier = "group", tag = OpenApiTag.GROUP),
                    requestFields(
                        "name" type STRING means "그룹 이름",
                        "description" type STRING means "그룹 설명",
                        "organization" type STRING means "그룹 소속",
                        "profileImageUrl" type STRING means "그룹 프로필 이미지 URL" isOptional true,
                        "ownerId" type NUMBER means "그룹장 ID",
                        "nickname" type STRING means "그룹장 닉네임",
                    ),
                    responseFields(
                        "id" type NUMBER means "그룹 ID",
                        "accessCode" type STRING means "그룹 접근 코드",
                    )
                )
        }
    }
}