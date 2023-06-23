package com.yapp.bol.group.member

import com.yapp.bol.group.GroupId
import com.yapp.bol.pagination.CursorRequest
import com.yapp.bol.pagination.SimpleCursorRequest
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
        context("findByGroupIdWithCursor") {
            val groupId = GroupId(68)
            val size = 20
            val member = MemberEntity(userId = 123, nickname = "닉네임", role = MemberRole.OWNER)

            test("커서 존재하지 않음") {
                clearAllMocks()
                val request = SimpleCursorRequest<String>(size, null)
                val cursorRequest = CapturingSlot<CursorRequest<String>>()
                every {
                    memberRepository.getByGroupIdWithCursor(
                        groupId.value,
                        null,
                        capture(cursorRequest),
                    )
                } returns listOf(member)

                val result = sut.getMemberListByCursor(groupId, null, request)

                cursorRequest.captured.size shouldBeEqual size
                cursorRequest.captured.cursor shouldBe null
                result.size shouldBeEqual 1
                result[0] shouldBeEqual member
                verify(exactly = 1) { memberRepository.getByGroupIdWithCursor(groupId.value, null, any()) }
            }

            test("커서 존재함") {
                clearAllMocks()
                val cursor = "cursor"
                val request = SimpleCursorRequest(size, cursor)
                val cursorRequest = CapturingSlot<CursorRequest<String>>()
                every {
                    memberRepository.getByGroupIdWithCursor(
                        groupId.value,
                        null,
                        capture(cursorRequest),
                    )
                } returns listOf(member)

                val result = sut.getMemberListByCursor(groupId, null, request)

                cursorRequest.captured.size shouldBeEqual size
                cursorRequest.captured.cursor shouldBe cursor
                result.size shouldBeEqual 1
                result[0] shouldBeEqual member
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
