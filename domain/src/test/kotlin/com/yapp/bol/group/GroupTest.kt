package com.yapp.bol.group

import com.yapp.bol.InvalidGroupDescriptionException
import com.yapp.bol.InvalidGroupNameException
import com.yapp.bol.InvalidGroupOrganizationException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class GroupTest : FunSpec() {
    init {
        val group = Group(
            name = "name",
            description = "description",
            organization = "organization",
        )

        test("그룹 생성") {
            group.shouldBeInstanceOf<Group>()
        }

        test("그룹 이름 길이 제한") {
            val name = "x".repeat(Group.MAX_NAME_LENGTH + 1)

            shouldThrow<InvalidGroupNameException> {
                Group(name = name, description = "description", organization = "organization")
            }
        }

        test("그룹 설명 길이 제한") {
            val description = "x".repeat(Group.MAX_DESCRIPTION_LENGTH + 1)

            shouldThrow<InvalidGroupDescriptionException> {
                Group(name = "name", description = description, organization = "organization")
            }
        }

        test("그룹 소속 길이 제한") {
            val organization = "x".repeat(Group.MAX_ORGANIZATION_LENGTH + 1)

            shouldThrow<InvalidGroupOrganizationException> {
                Group(name = "name", description = "description", organization = organization)
            }
        }

        test("그룹 프로필 이미지 URL 이 비었다면 디폴트 이미지로 설정되어야 한다") {
            group.profileImageUrl shouldBe Group.DEFAULT_PROFILE_IMAGE_URL
        }

        test("accessCode 가 자동으로 생성된다") {
            group.accessCode.shouldBeInstanceOf<String>()
            group.accessCode.length shouldBe Group.ACCESS_CODE_LENGTH
        }
    }
}
