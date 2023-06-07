package com.yapp.bol.member

import com.yapp.bol.group.member.Member
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.types.shouldBeInstanceOf

class MemberTest : FunSpec() {
    init {
        test("멤버 생성") {
            val member = Member(0, null, "OWNER", "nickname")

            member.shouldBeInstanceOf<Member>()
        }

        test("멤버 닉네임 길이 제한") {
            val nickname = "x".repeat(Member.MAX_NICKNAME_LENGTH + 1)

            shouldThrow<IllegalArgumentException> {
                Member(0, null, "OWNER", nickname)
            }
        }
    }
}
