package com.yapp.bol.group.member

import com.yapp.bol.InvalidMemberNicknameException

class Member(
    val id: Long,
    val userId: Long? = null,
    val role: MemberRole, // FIXME: 스프링 시큐리티 추가 전 임시 값
    val nickname: String,
) {
    init {
        if (nickname.length > MAX_NICKNAME_LENGTH) {
            throw InvalidMemberNicknameException
        }
    }

    fun isOwner(): Boolean = this.role == MemberRole.OWNER

    companion object {
        const val MAX_NICKNAME_LENGTH = 6

        fun createOwner(userId: Long, nickname: String): Member {
            return Member(
                id = 0,
                userId = userId,
                role = MemberRole.OWNER,
                nickname = nickname,
            )
        }
    }
}
