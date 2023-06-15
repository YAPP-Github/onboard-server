package com.yapp.bol.group.member

import com.yapp.bol.InvalidMemberNicknameException
import com.yapp.bol.auth.UserId
import com.yapp.bol.group.GroupId

@JvmInline
value class MemberId(val value: Long)

class Member(
    val id: MemberId = MemberId(0),
    val userId: UserId? = null,
    val role: MemberRole, // FIXME: 스프링 시큐리티 추가 전 임시 값
    val nickname: String,
    val groupId: GroupId,
) {
    init {
        if (nickname.length > MAX_NICKNAME_LENGTH) {
            throw InvalidMemberNicknameException
        }
    }

    fun isOwner(): Boolean = this.role == MemberRole.OWNER

    companion object {
        const val MAX_NICKNAME_LENGTH = 6

        fun createOwner(userId: UserId, nickname: String, groupId: GroupId): Member {
            return Member(
                userId = userId,
                role = MemberRole.OWNER,
                nickname = nickname,
                groupId = groupId,
            )
        }
    }
}
