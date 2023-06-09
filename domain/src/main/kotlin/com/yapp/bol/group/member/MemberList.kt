package com.yapp.bol.group.member

class MemberList(val members: MutableList<Member>) {
    init {
        require(members.isNotEmpty()) { "멤버는 최소 1명 이상이어야 합니다." }
        require(validateDistinctNicknames(members)) { DUPLICATED_NICKNAME_EXCEPTION_MESSAGE }
    }

    fun toList(): List<Member> {
        return members.toList()
    }

    fun add(member: Member) {
        require(validateDistinctNickname(member)) { DUPLICATED_NICKNAME_EXCEPTION_MESSAGE }

        members.add(member)
    }

    fun findMemberByNickname(nickname: String): Member? {
        return members.find { it.nickname == nickname }
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
        private const val DUPLICATED_NICKNAME_EXCEPTION_MESSAGE = "멤버의 닉네임은 중복될 수 없습니다."

        fun of(owner: Member): MemberList {
            validateOwnerRole(owner)

            return MemberList(mutableListOf(owner))
        }

        private fun validateOwnerRole(owner: Member) {
            if (!owner.isOwner()) {
                throw IllegalArgumentException("그룹 생성자는 OWNER 역할이어야 합니다.")
            }
        }
    }
}
