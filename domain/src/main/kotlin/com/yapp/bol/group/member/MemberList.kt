package com.yapp.bol.group.member

import com.yapp.bol.DuplicatedMembersNicknameException
import com.yapp.bol.MultiOwnerException

class MemberList(
    val owner: OwnerMember,
    val members: List<Member> = emptyList(),
) {
    private val totalList: List<Member>
        get() = members + owner

    init {
        if (validateDistinctNicknames(totalList).not()) {
            throw DuplicatedMembersNicknameException
        }

        if (members.any(Member::isOwner)) {
            throw MultiOwnerException
        }
    }

    fun toList(): List<Member> {
        return totalList
    }

    fun findMemberByNickname(nickname: String): Member? {
        return members.find { it.nickname == nickname }
    }

    private fun validateDistinctNicknames(members: List<Member>): Boolean =
        members.size == members
            .map { it.nickname }
            .distinct()
            .size

    private fun validateDistinctNickname(hostMember: Member): Boolean =
        this.members
            .all { it.nickname != hostMember.nickname }
}
