package com.yapp.bol.group

import com.yapp.bol.AccessCodeNotMatchException
import com.yapp.bol.NotFoundGroupException
import com.yapp.bol.auth.UserId
import com.yapp.bol.group.dto.JoinGroupDto
import com.yapp.bol.group.member.HostMember
import com.yapp.bol.group.member.MemberService
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.mockk.every
import io.mockk.mockk

class GroupServiceImplTest : FunSpec() {
    private val groupQueryRepository: GroupQueryRepository = mockk()
    private val groupCommandRepository: GroupCommandRepository = mockk()
    private val memberService: MemberService = mockk()
    private val sut = GroupServiceImpl(groupQueryRepository, groupCommandRepository, memberService)

    init {
        context("Join Group Test") {
            val request = JoinGroupDto(
                groupId = GroupId(0),
                userId = UserId(0),
                nickname = "nickname",
                accessCode = "accessCode",
            )

            val mockGroup = Group(
                name = "name",
                description = "description",
                organization = "organization",
                profileImageUrl = "profileImageUrl",
                accessCode = "accessCode"
            )

            test("Success") {
                every { groupQueryRepository.findById(request.groupId.value) } returns mockGroup
                every { memberService.createHostMember(any(), any(), any()) } returns HostMember(
                    userId = request.userId,
                    nickname = request.nickname,
                )

                shouldNotThrow<Exception> {
                    sut.joinGroup(request)
                }
            }

            test("존재하지 않는 그룹") {
                every { groupQueryRepository.findById(request.groupId.value) } returns null

                shouldThrow<NotFoundGroupException> {
                    sut.joinGroup(request)
                }
            }

            test("엑세스 코드 불일치") {
                every { groupQueryRepository.findById(request.groupId.value) } returns mockGroup

                shouldThrow<AccessCodeNotMatchException> {
                    sut.joinGroup(request.copy(accessCode = "qwerty"))
                }
            }
        }
    }
}
