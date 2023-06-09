package com.yapp.bol.group.member

class Member(
    val id: Long,
    val userId: Long? = null,
    val groupId: Long? = null,
    val role: MemberRole, // FIXME: 스프링 시큐리티 추가 전 임시 값
    val nickname: String,
) {
    init {
        require(nickname.length <= MAX_NICKNAME_LENGTH) { "닉네임은 $MAX_NICKNAME_LENGTH 자 이내여야 합니다." }
    }

    fun isOwner(): Boolean = this.role == MemberRole.OWNER

    companion object {
        const val MAX_NICKNAME_LENGTH = 6

        fun createOwner(userId: Long, groupId: Long, nickname: String): Member {
            return Member(
                id = 0,
                userId = userId,
                groupId = groupId,
                role = MemberRole.OWNER,
                nickname = nickname
            )
        }
    }
}
