package com.yapp.bol.group.member

import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class MemberServiceImplTest : FunSpec() {
    private val memberQueryRepository: MemberQueryRepository = mockk()
    private val memberCommandRepository: MemberCommandRepository = mockk()
    private val sut = MemberServiceImpl(memberQueryRepository, memberCommandRepository)

    init {
        context("validateMemberNickname") {
            val groupId = GroupId(0)
            val nickname = "닉네임"

            test("Success") {
                val groupId = GroupId(0)
                val nickname = "닉네임"

                every { memberQueryRepository.findByNicknameAndGroupId(nickname, groupId) } returns null

                sut.validateMemberNickname(groupId, nickname) shouldBe true
            }

            test("닉네임 중복") {
                val mockMember = HostMember(
                    userId = UserId(0),
                    nickname = nickname,
                )

                every { memberQueryRepository.findByNicknameAndGroupId(nickname, groupId) } returns mockMember

                sut.validateMemberNickname(groupId, nickname) shouldBe false
            }

            test("닉네임 길이 초과") {
                val nickname = "x".repeat(Member.MAX_NICKNAME_LENGTH + 1)

                every { memberQueryRepository.findByNicknameAndGroupId(nickname, groupId) } returns null

                sut.validateMemberNickname(groupId, nickname) shouldBe false
            }

            test("닉네임 길이 부족") {
                val nickname = "x".repeat(Member.MIN_NICKNAME_LENGTH - 1)

                every { memberQueryRepository.findByNicknameAndGroupId(nickname, groupId) } returns null

                sut.validateMemberNickname(groupId, nickname) shouldBe false
            }
        }
    }
}
