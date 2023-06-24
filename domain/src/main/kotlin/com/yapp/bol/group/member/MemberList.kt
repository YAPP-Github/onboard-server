package com.yapp.bol.group.member

import com.yapp.bol.DuplicatedMembersNicknameException

class MemberList(
    val owner: OwnerMember,
    val participant: List<ParticipantMember> = emptyList(),
) {
    private val totalList: List<Member>
        get() = participant + owner

    init {
        if (validateDistinctNicknames(totalList).not()) {
            throw DuplicatedMembersNicknameException
        }
    }

    fun toList(): List<Member> {
        return totalList
    }

    fun getSize(): Int {
        return totalList.size
    }

    private fun validateDistinctNicknames(members: List<Member>): Boolean =
        members.size == members
            .map { it.nickname }
            .distinct()
            .size

    private fun validateDistinctNickname(hostMember: Member): Boolean =
        this.participant
            .all { it.nickname != hostMember.nickname }
}
