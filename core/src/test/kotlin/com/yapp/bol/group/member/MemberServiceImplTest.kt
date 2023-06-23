package com.yapp.bol.group.member

import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.dto.GetMembersByCursorDto
import com.yapp.bol.pagination.CursorRequest
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.mockk.CapturingSlot
import io.mockk.every
import io.mockk.mockk

class MemberServiceImplTest : FunSpec() {
    private val memberQueryRepository: MemberQueryRepository = mockk()
    private val memberCommandRepository: MemberCommandRepository = mockk()
    private val sut = MemberServiceImpl(memberQueryRepository, memberCommandRepository)

    init {
        context("getMembers") {
            val groupId = GroupId(68)
            val size = 20
            val request = GetMembersByCursorDto(groupId, null, size, null)
            val cursor = CapturingSlot<CursorRequest<String>>()

            val memberList = List(size + 1) {
                HostMember(userId = UserId(it.toLong()), nickname = "닉네임$it")
            }

            test("hasNext = false 테스트") {
                every {
                    memberQueryRepository.getMemberListByCursor(
                        groupId,
                        null,
                        capture(cursor),
                    )
                } returns memberList.take(size)

                val result = sut.getMembers(request)

                result.contents.size shouldBeEqual size
                result.cursor shouldBeEqual memberList[size - 1].nickname
                result.hasNext shouldBeEqual false
                cursor.captured.size shouldBeEqual size + 1
            }

            test("hasNext = true 테스트") {
                every {
                    memberQueryRepository.getMemberListByCursor(
                        groupId,
                        null,
                        capture(cursor),
                    )
                } returns memberList.take(size + 1)

                val result = sut.getMembers(request)

                result.contents.size shouldBeEqual size
                result.cursor shouldBeEqual memberList[size - 1].nickname
                result.hasNext shouldBeEqual true
                cursor.captured.size shouldBeEqual size + 1
            }
        }
    }
}
