package com.yapp.bol

import java.lang.RuntimeException

sealed class BolRatingException(
    val code: String,
    val status: Int,
    override val message: String,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)

sealed class AuthException(code: String, status: Int, message: String, cause: Throwable? = null) : BolRatingException(code, status, message, cause)

class SocialLoginFailedException(cause: Throwable? = null) : AuthException("Auth001", 400, "로그인이 실패했습니다.", cause)
object InvalidTokenException : AuthException("Auth002", 400, "유효하지 않는 토큰 입니다.")
object ExpiredTokenException : AuthException("Auth003", 400, "만료된 토큰 입니다.")
class UnAuthenticationException(cause: Throwable? = null) : AuthException("Auth004", 401, "로그인이 필요합니다.", cause)
class UnAuthorizationException(cause: Throwable? = null) : AuthException("Auth005", 403, "권한이 없습니다.", cause)

object IllegalFileStateException : BolRatingException("File001", 500, "요청한 파일의 Status가 올바르지 않습니다.")
object NotFoundFileException : BolRatingException("File002", 400, "파일을 찾을 수 없습니다.")

object InvalidRequestException : BolRatingException("BOL001", 400, "유효하지 않은 요청입니다.")

sealed class GroupException(code: String, message: String, cause: Throwable? = null) :
    BolRatingException(code = code, status = 400, message = message, cause = cause)

object InvalidGroupNameException : GroupException("Group001", "그룹 이름이 잘못되었습니다.")

object InvalidGroupDescriptionException :
    GroupException("Group002", "그룹 설명이 잘못되었습니다.")

object InvalidGroupOrganizationException :
    GroupException("Group003", "그룹 소속이 잘못되었습니다.")

sealed class MemberException(code: String, message: String, cause: Throwable? = null) :
    BolRatingException(code = code, status = 400, message = message, cause = cause)

object InvalidMemberNicknameException : MemberException("Member001", "멤버 닉네임이 잘못되었습니다.")

object EmptyMemberListException : MemberException("Member002", "멤버는 최소 1명 이상이어야 합니다.")

object DuplicatedMemberNicknameException : MemberException("Member003", "중복된 멤버 닉네임입니다.")

object DuplicatedMembersNicknameException : MemberException("Member004", "멤버들 간에 중복된 닉네임이 존재합니다.")

object NoOwnerException : MemberException("Member005", "그룹장이 존재하지 않습니다.")
