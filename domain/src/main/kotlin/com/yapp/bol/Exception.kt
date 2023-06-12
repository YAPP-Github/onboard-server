package com.yapp.bol

import java.lang.RuntimeException

sealed class BolRatingException(
    val code: String,
    override val message: String,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)

sealed class LoginException(code: String, msg: String, cause: Throwable? = null) : BolRatingException(code, msg, cause)

class SocialLoginFailedException(cause: Throwable? = null) : LoginException("Auth001", "로그인이 실패했습니다.", cause)
object InvalidTokenException : LoginException("Auth002", "유효하지 않는 토큰 입니다.")
object ExpiredTokenException : LoginException("Auth003", "만료된 토큰 입니다.")

sealed class GroupException(code: String, msg: String, cause: Throwable? = null) : BolRatingException(code, msg, cause)

object InvalidGroupNameException : GroupException("Group001", "그룹 이름이 잘못되었습니다.")

object InvalidGroupDescriptionException :
    GroupException("Group002", "그룹 설명이 잘못되었습니다.")

object InvalidGroupOrganizationException :
    GroupException("Group003", "그룹 소속이 잘못되었습니다.")

sealed class MemberException(code: String, msg: String, cause: Throwable? = null) : BolRatingException(code, msg, cause)

object InvalidMemberNicknameException : MemberException("Member001", "멤버 닉네임이 잘못되었습니다.")

object EmptyMemberListException : MemberException("Member002", "멤버는 최소 1명 이상이어야 합니다.")

object DuplicatedMemberNicknameException : MemberException("Member003", "중복된 멤버 닉네임입니다.")

object DuplicatedMembersNicknameException : MemberException("Member004", "멤버들 간에 중복된 닉네임이 존재합니다.")

object NoOwnerException : MemberException("Member005", "그룹장이 존재하지 않습니다.")
