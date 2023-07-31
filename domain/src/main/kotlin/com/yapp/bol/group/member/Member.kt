package com.yapp.bol.group.member

import com.yapp.bol.InvalidMemberRoleException
import com.yapp.bol.auth.UserId
import com.yapp.bol.validate.NicknameValidator

@JvmInline
value class MemberId(val value: Long)

abstract class Member internal constructor(
    val id: MemberId,
    val userId: UserId?,
    val nickname: String,
    val level: Int,
) {
    val role: MemberRole = when {
        isOwner() -> MemberRole.OWNER
        isGuest() -> MemberRole.GUEST
        isHost() -> MemberRole.HOST
        else -> throw InvalidMemberRoleException
    }

    init {
        NicknameValidator.validate(nickname)

        if (userId == null && isGuest().not()) throw InvalidMemberRoleException
    }

    fun isOwner(): Boolean = this is OwnerMember
    fun isGuest(): Boolean = userId == null || this is GuestMember
    fun isHost(): Boolean = this is HostMember

    companion object {
        const val MAX_NICKNAME_LENGTH = 10
        const val MIN_NICKNAME_LENGTH = 1
    }
}
