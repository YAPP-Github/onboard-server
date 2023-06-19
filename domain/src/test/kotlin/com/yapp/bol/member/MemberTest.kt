package com.yapp.bol.member

import com.yapp.bol.InvalidMemberNicknameException
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.Member
import com.yapp.bol.group.member.MemberId
import com.yapp.bol.group.member.MemberRole
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.types.shouldBeInstanceOf

val MEMBER_OWNER = Member(MemberId(0), null, MemberRole.OWNER, "nick", GroupId(0))

class MemberTest : FunSpec() {
    init {
        test("멤버 생성") {
            MEMBER_OWNER.shouldBeInstanceOf<Member>()
        }

        test("멤버 닉네임 길이 제한") {
            val nickname = "x".repeat(Member.MAX_NICKNAME_LENGTH + 1)

            shouldThrow<InvalidMemberNicknameException> {
                Member(MemberId(0), null, MemberRole.OWNER, nickname, GroupId(0))
            }
        }
    }
}
