package yapp.rating.auth

import java.lang.UnsupportedOperationException

enum class SocialType {
    KAKAO,
    NAVER,
    GOOGLE,
}

fun LoginType.toSocialType(): SocialType =
    when (this) {
        LoginType.KAKAO -> SocialType.KAKAO
        LoginType.NAVER -> SocialType.NAVER
        LoginType.GOOGLE -> SocialType.GOOGLE
        LoginType.REFRESH -> throw UnsupportedOperationException()
    }
