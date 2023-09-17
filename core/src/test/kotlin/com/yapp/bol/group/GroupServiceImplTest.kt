package com.yapp.bol.group

import com.yapp.bol.AccessCodeNotMatchException
import com.yapp.bol.NotFoundGroupException
import com.yapp.bol.UnAuthorizationException
import com.yapp.bol.auth.UserId
import com.yapp.bol.group.dto.AddGuestDto
import com.yapp.bol.group.dto.JoinGroupDto
import com.yapp.bol.group.member.GuestMember
import com.yapp.bol.group.member.HostMember
import com.yapp.bol.group.member.MemberCommandRepository
import com.yapp.bol.group.member.MemberQueryRepository
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
    private val memberQueryRepository: MemberQueryRepository = mockk()
    private val memberCommandRepository: MemberCommandRepository = mockk()

    private val sut = GroupServiceImpl(
        groupQueryRepository,
        groupCommandRepository,
        memberService,
        memberQueryRepository,
        memberCommandRepository
    )

    init {
        context("Join Group Test") {
            val request = JoinGroupDto(
                groupId = GroupId(0),
                userId = UserId(0),
                nickname = "닉네임",
                accessCode = "123456",
                guestId = null,
            )

            val mockGroup = Group(
                name = "name",
                description = "description",
                organization = "organization",
                profileImageUrl = "profileImageUrl",
                accessCode = request.accessCode
            )

            test("Success") {
                every { groupQueryRepository.findById(request.groupId) } returns mockGroup
                every { memberQueryRepository.findByGroupIdAndUserId(request.groupId, request.userId) } returns null
                every { memberService.createHostMember(any(), any(), any()) } returns HostMember(
                    userId = request.userId,
                    nickname = request.nickname!!,
                )

                shouldNotThrow<Exception> {
                    sut.joinGroup(request)
                }
            }

            test("존재하지 않는 그룹") {
                every { groupQueryRepository.findById(request.groupId) } returns null

                shouldThrow<NotFoundGroupException> {
                    sut.joinGroup(request)
                }
            }

            test("엑세스 코드 불일치") {
                every { groupQueryRepository.findById(request.groupId) } returns mockGroup

                shouldThrow<AccessCodeNotMatchException> {
                    sut.joinGroup(request.copy(accessCode = "qwerty"))
                }
            }
        }

        context("Add Guest") {
            val request = AddGuestDto(
                groupId = GroupId(0),
                nickname = "name",
                requestUserId = UserId(0)
            )

            val mockMember = HostMember(
                userId = request.requestUserId,
                nickname = "other"
            )

            test("Success") {
                every {
                    memberQueryRepository.findByGroupIdAndUserId(
                        request.groupId,
                        request.requestUserId
                    )
                } returns mockMember
                every { memberService.createGuestMember(any(), any()) } returns GuestMember(
                    nickname = request.nickname,
                )

                shouldNotThrow<Exception> {
                    sut.addGuest(request)
                }
            }

            test("요청자가 가입한 그룹이 아님") {
                every {
                    memberQueryRepository.findByGroupIdAndUserId(
                        request.groupId,
                        request.requestUserId
                    )
                } returns null
                every { memberService.createGuestMember(any(), any()) } returns GuestMember(
                    nickname = request.nickname,
                )

                shouldThrow<UnAuthorizationException> {
                    sut.addGuest(request)
                }
            }
        }
    }
}
