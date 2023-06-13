package com.yapp.bol.group.member

import com.yapp.bol.InvalidMemberNicknameException

class Member(
    val id: Long = 0,
    val userId: Long? = null,
    val role: MemberRole, // FIXME: 스프링 시큐리티 추가 전 임시 값
    val nickname: String,
    val groupId: Long,
) {
    init {
        if (nickname.length > MAX_NICKNAME_LENGTH) {
            throw InvalidMemberNicknameException
        }
    }

    fun isOwner(): Boolean = this.role == MemberRole.OWNER

    companion object {
        const val MAX_NICKNAME_LENGTH = 6

        fun createOwner(userId: Long, nickname: String, groupId: Long): Member {
            return Member(
                userId = userId,
                role = MemberRole.OWNER,
                nickname = nickname,
                groupId = groupId,
            )
        }
    }
}
