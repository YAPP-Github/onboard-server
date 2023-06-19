package com.yapp.bol.member

import com.yapp.bol.InvalidMemberNicknameException
import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.GuestMember
import com.yapp.bol.group.member.HostMember
import com.yapp.bol.group.member.Member
import com.yapp.bol.group.member.OwnerMember
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.types.shouldBeInstanceOf

val MEMBER_OWNER = OwnerMember(userId = UserId(0), nickname = "nick", groupId = GroupId(0))

class MemberTest : FunSpec() {
    init {
        test("맴버 생성") {
            MEMBER_OWNER.shouldBeInstanceOf<Member>()
        }

        test("맴버 닉네임 길이 제한") {
            val nickname = "x".repeat(Member.MAX_NICKNAME_LENGTH + 1)

            shouldThrow<InvalidMemberNicknameException> {
                OwnerMember(userId = UserId(0), nickname = nickname, groupId = GroupId(0))
            }
            shouldThrow<InvalidMemberNicknameException> {
                HostMember(userId = UserId(0), nickname = nickname, groupId = GroupId(0))
            }
            shouldThrow<InvalidMemberNicknameException> {
                GuestMember(nickname = nickname, groupId = GroupId(0))
            }
        }
    }
}
