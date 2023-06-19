package com.yapp.bol.member

import com.yapp.bol.DuplicatedMembersNicknameException
import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.HostMember
import com.yapp.bol.group.member.MemberList
import com.yapp.bol.group.member.OwnerMember
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.types.shouldBeInstanceOf

class MemberListTest : FunSpec() {
    init {
        test("맴버 리스트 생성") {
            val members = MemberList(MEMBER_OWNER)
            members.shouldBeInstanceOf<MemberList>()
        }

        test("맴버 리스트 생성시 맴버의 닉네임은 중복될 수 없다.") {
            val nickname = "holden"
            val owner = OwnerMember(userId = UserId(1), nickname = nickname, groupId = GroupId(0))
            val member = HostMember(userId = UserId(2), nickname = nickname, groupId = GroupId(0))

            shouldThrow<DuplicatedMembersNicknameException> {
                MemberList(owner, mutableListOf(member))
            }
        }
    }
}
