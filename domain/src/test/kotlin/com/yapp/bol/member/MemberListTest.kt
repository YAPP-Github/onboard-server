package com.yapp.bol.member

import com.yapp.bol.DuplicatedMemberNicknameException
import com.yapp.bol.DuplicatedMembersNicknameException
import com.yapp.bol.EmptyMemberListException
import com.yapp.bol.group.member.Member
import com.yapp.bol.group.member.MemberList
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.types.shouldBeInstanceOf

class MemberListTest : FunSpec() {
    init {
        test("멤버 리스트 생성") {
            val members = MemberList.of(MEMBER_OWNER)

            members.shouldBeInstanceOf<MemberList>()
        }

        test("멤버 리스트 생성시 멤버는 최소 1명 이상이어야 한다.") {
            shouldThrow<EmptyMemberListException> {
                MemberList(mutableListOf())
            }
        }

        test("멤버 리스트 생성시 멤버의 닉네임은 중복될 수 없다.") {
            val nickname = "holden"
            val member = Member.createOwner(1, nickname, 0)
            val member2 = Member.createOwner(2, nickname, 0)

            shouldThrow<DuplicatedMembersNicknameException> {
                MemberList(mutableListOf(member, member2))
            }
        }

        test("멤버 리스트에 닉네임이 중복된 멤버는 추가할 수 없다") {
            val nickname = "holden"
            val member = Member.createOwner(1, nickname, 0)
            val member2 = Member.createOwner(2, nickname, 0)

            val memberList = MemberList(mutableListOf(member))

            shouldThrow<DuplicatedMemberNicknameException> {
                memberList.add(member2)
            }
        }
    }
}
