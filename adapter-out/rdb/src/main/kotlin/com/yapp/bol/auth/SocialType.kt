package com.yapp.bol.auth

import java.lang.UnsupportedOperationException

enum class SocialType {
    KAKAO,
    NAVER,
    GOOGLE,
}

fun LoginType.toSocialType(): SocialType =
    when (this) {
        LoginType.KAKAO -> SocialType.KAKAO
        LoginType.NAVER_ACCESS_TOKEN -> SocialType.NAVER
        LoginType.GOOGLE -> SocialType.GOOGLE
        LoginType.REFRESH -> throw UnsupportedOperationException()
    }
