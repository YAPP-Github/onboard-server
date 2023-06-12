package com.yapp.bol.member

import com.yapp.bol.InvalidMemberNicknameException
import com.yapp.bol.group.member.Member
import com.yapp.bol.group.member.MemberRole
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.types.shouldBeInstanceOf

class MemberTest : FunSpec() {
    init {
        test("멤버 생성") {
            val member = Member(0, null, MemberRole.OWNER, "nick")

            member.shouldBeInstanceOf<Member>()
        }

        test("멤버 닉네임 길이 제한") {
            val nickname = "x".repeat(Member.MAX_NICKNAME_LENGTH + 1)

            shouldThrow<InvalidMemberNicknameException> {
                Member(0, null, MemberRole.OWNER, nickname)
            }
        }
    }
}
