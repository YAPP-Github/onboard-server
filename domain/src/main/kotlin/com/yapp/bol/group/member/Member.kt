package com.yapp.bol.group.member

class Member(
    val id: Long,
    val userId: Long? = null,
    val role: String?, // FIXME: 스프링 시큐리티 추가 전 임시 값
    val nickname: String,
) {
    init {
        require(nickname.length <= MAX_NICKNAME_LENGTH) { "닉네임은 $MAX_NICKNAME_LENGTH 자 이내여야 합니다." }
    }

    companion object {
        const val MAX_NICKNAME_LENGTH = 6

        fun createOwner(userId: Long, nickname: String): Member {
            return Member(0, userId, "OWNER", nickname)
        }
    }
}
