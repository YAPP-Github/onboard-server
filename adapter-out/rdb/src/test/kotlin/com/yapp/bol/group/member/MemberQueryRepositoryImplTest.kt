package com.yapp.bol.group.member

import com.yapp.bol.group.GroupId
import com.yapp.bol.pagination.SimpleCursorRequest
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.CapturingSlot
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.data.domain.Pageable

class MemberQueryRepositoryImplTest : FunSpec() {
    private val memberRepository: MemberRepository = mockk()
    private val sut = MemberQueryRepositoryImpl(memberRepository)

    init {
        context("findByGroupIdWithCursor") {
            val groupId = GroupId(68)
            val size = 20
            val member = MemberEntity(userId = 123, nickname = "닉네임", role = MemberRole.OWNER)

            test("커서 존재하지 않음") {
                val cursorRequest = SimpleCursorRequest<String>(size, null)
                val pageable = CapturingSlot<Pageable>()
                every {
                    memberRepository.getByGroupIdWithCursor(
                        groupId.value,
                        capture(pageable),
                    )
                } returns listOf(member)

                val result = sut.findByGroupIdWithCursor(groupId, cursorRequest)

                pageable.captured.pageSize shouldBeEqual size
                pageable.captured.pageNumber shouldBeEqual 0
                result.size shouldBeEqual 1
                result[0] shouldBeEqual member
                verify(exactly = 1) { memberRepository.getByGroupIdWithCursor(groupId.value, any()) }
            }

            test("커서 존재함") {
                val cursor = "cursor"
                val cursorRequest = SimpleCursorRequest(size, cursor)
                val pageable = CapturingSlot<Pageable>()
                every {
                    memberRepository.getByGroupIdWithCursor(
                        groupId.value,
                        cursor,
                        capture(pageable),
                    )
                } returns listOf(member)

                val result = sut.findByGroupIdWithCursor(groupId, cursorRequest)

                pageable.captured.pageSize shouldBeEqual size
                pageable.captured.pageNumber shouldBeEqual 0
                result.size shouldBeEqual 1
                result[0] shouldBeEqual member
                verify(exactly = 1) { memberRepository.getByGroupIdWithCursor(groupId.value, cursor, any()) }
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
