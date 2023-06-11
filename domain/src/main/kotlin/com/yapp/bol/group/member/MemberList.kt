package com.yapp.bol.group.member

import com.yapp.bol.DuplicatedMemberNicknameException
import com.yapp.bol.DuplicatedMembersNicknameException
import com.yapp.bol.EmptyMemberListException
import com.yapp.bol.NoOwnerException

class MemberList(val members: MutableList<Member>) {
    init {
        if (members.isEmpty()) {
            throw EmptyMemberListException
        }

        if (validateDistinctNicknames(members).not()) {
            throw DuplicatedMembersNicknameException
        }

        if (members.any(Member::isOwner).not()) {
            throw NoOwnerException
        }
    }

    fun toList(): List<Member> {
        return members.toList()
    }

    fun add(member: Member) {
        if (!validateDistinctNickname(member)) {
            throw DuplicatedMemberNicknameException
        }

        members.add(member)
    }

    fun findMemberByNickname(nickname: String): Member? {
        return members.find { it.nickname == nickname }
    }

    fun getOwner(): Member {
        return members.find(Member::isOwner) ?: throw NoOwnerException
    }

    private fun validateDistinctNicknames(members: List<Member>): Boolean = (
        members.size == members
            .map { it.nickname }
            .distinct()
            .size
        )

    private fun validateDistinctNickname(member: Member): Boolean = (
        this.members
            .all { it.nickname != member.nickname }
        )

    companion object {
        fun of(owner: Member) = MemberList(mutableListOf(owner))
    }
}
