package com.yapp.bol.group.member

class MemberList(val members: MutableList<Member>) {
    fun toList(): List<Member> {
        return members.toList()
    }

    fun add(member: Member) {
        members.add(member)
    }

    companion object {
        fun of(owner: Member): MemberList {
            if (!owner.isOwner()) {
                throw IllegalArgumentException("그룹 생성자는 OWNER 역할이어야 합니다.")
            }

            return MemberList(mutableListOf(owner))
        }
    }
}
