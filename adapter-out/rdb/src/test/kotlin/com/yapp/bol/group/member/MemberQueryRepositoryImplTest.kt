package com.yapp.bol.group.member

import com.yapp.bol.group.GroupId
import com.yapp.bol.pagination.cursor.CursorRequest
import com.yapp.bol.pagination.cursor.member.MemberCursorRequest
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.CapturingSlot
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class MemberQueryRepositoryImplTest : FunSpec() {
    private val memberRepository: MemberRepository = mockk()
    private val sut = MemberQueryRepositoryImpl(memberRepository)

    init {
        context("getMemberListByCursor") {
            val groupId = GroupId(68)
            val size = 20
            val member = MemberEntity(userId = 123, nickname = "닉네임", role = MemberRole.OWNER)

            test("커서 존재하지 않음") {
                clearAllMocks()
                val request = MemberCursorRequest(groupId, null, size, null)
                val cursorRequest = CapturingSlot<CursorRequest<String>>()
                every {
                    memberRepository.getByGroupIdWithCursor(
                        groupId.value,
                        null,
                        capture(cursorRequest),
                    )
                } returns listOf(member)

                val response = sut.getMemberListByCursor(request)

                cursorRequest.captured.size shouldBeEqual size + 1
                cursorRequest.captured.cursor shouldBe null

                response.contents.size shouldBeEqual 1
                response.contents.first() shouldBeEqual member
                verify(exactly = 1) { memberRepository.getByGroupIdWithCursor(groupId.value, null, any()) }
            }

            test("커서 존재함") {
                clearAllMocks()
                val cursor = "cursor"
                val request = MemberCursorRequest(groupId, null, size, cursor)
                val cursorRequest = CapturingSlot<CursorRequest<String>>()
                every {
                    memberRepository.getByGroupIdWithCursor(
                        groupId.value,
                        null,
                        capture(cursorRequest),
                    )
                } returns listOf(member)

                val response = sut.getMemberListByCursor(request)

                cursorRequest.captured.size shouldBeEqual size + 1
                cursorRequest.captured.cursor shouldBe cursor

                response.contents.size shouldBeEqual 1
                response.contents.first() shouldBeEqual member
                verify(exactly = 1) { memberRepository.getByGroupIdWithCursor(groupId.value, null, any()) }
            }

            test("hasNext = false 테스트") {
                clearAllMocks()
                val request = MemberCursorRequest(groupId, null, size, null)
                val cursorRequest = CapturingSlot<CursorRequest<String>>()

                every {
                    memberRepository.getByGroupIdWithCursor(
                        groupId.value,
                        null,
                        capture(cursorRequest),
                    )
                } returns List(size) { member }

                val response = sut.getMemberListByCursor(request)

                cursorRequest.captured.size shouldBeEqual size + 1
                cursorRequest.captured.cursor shouldBe null

                response.contents.size shouldBeEqual size
                response.hasNext shouldBeEqual false
                verify(exactly = 1) { memberRepository.getByGroupIdWithCursor(groupId.value, null, any()) }
            }

            test("hasNext = true 테스트") {
                clearAllMocks()
                val request = MemberCursorRequest(groupId, null, size, null)
                val cursorRequest = CapturingSlot<CursorRequest<String>>()

                every {
                    memberRepository.getByGroupIdWithCursor(
                        groupId.value,
                        null,
                        capture(cursorRequest),
                    )
                } returns List(size + 1) { member }

                val response = sut.getMemberListByCursor(request)

                cursorRequest.captured.size shouldBeEqual size + 1
                cursorRequest.captured.cursor shouldBe null

                response.contents.size shouldBeEqual size
                response.hasNext shouldBeEqual true
                verify(exactly = 1) { memberRepository.getByGroupIdWithCursor(groupId.value, null, any()) }
            }
        }
    }

    private infix fun Member.shouldBeEqual(entity: MemberEntity): Boolean {
        this.id.value shouldBeEqual entity.id
        if (this.userId != null) {
            entity.userId shouldNotBe null
            this.userId!!.value shouldBeEqual entity.userId!!
        } else {
            entity.userId shouldBe null
        }
        this.nickname shouldBeEqual entity.nickname
        this.level shouldBeEqual entity.level
        this.role shouldBeEqual entity.role

        return true
    }
}
