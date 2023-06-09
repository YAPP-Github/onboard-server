package com.yapp.bol.group

import com.yapp.bol.group.member.Member
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class GroupTest : FunSpec() {
    init {
        val owner = Member.createOwner(1, "nick")

        test("그룹 생성") {
            val group = Group.of("name", "description", "organization", owner)

            group.shouldBeInstanceOf<Group>()
        }

        test("그룹 이름 길이 제한") {
            val name = "x".repeat(Group.MAX_NAME_LENGTH + 1)

            shouldThrow<IllegalArgumentException> {
                Group.of(name, "description", "organization", owner)
            }
        }

        test("그룹 설명 길이 제한") {
            val description = "x".repeat(Group.MAX_DESCRIPTION_LENGTH + 1)

            shouldThrow<IllegalArgumentException> {
                Group.of("name", description, "organization", owner)
            }
        }

        test("그룹 소속 길이 제한") {
            val organization = "x".repeat(Group.MAX_ORGANIZATION_LENGTH + 1)

            shouldThrow<IllegalArgumentException> {
                Group.of("name", "description", organization, owner)
            }
        }

        test("그룹 프로필 이미지 URL 이 비었다면 디폴트 이미지로 설정되어야 한다") {
            val group = Group.of("name", "description", "organization", owner)

            group.profileImageUrl shouldBe Group.DEFAULT_PROFILE_IMAGE_URL
        }

        test("accessCode 가 자동으로 생성된다") {
            val group = Group.of("name", "description", "organization", owner)

            group.accessCode.shouldBeInstanceOf<String>()
            group.accessCode.length shouldBe Group.ACCESS_CODE_LENGTH
        }
    }
}
