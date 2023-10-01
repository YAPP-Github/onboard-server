package com.yapp.bol.auth

import java.lang.UnsupportedOperationException

enum class SocialType {
    KAKAO,
    NAVER,
    GOOGLE,
    APPLE,
}

fun LoginType.toSocialType(): SocialType =
    when (this) {
        LoginType.KAKAO_ACCESS_TOKEN -> SocialType.KAKAO
        LoginType.NAVER_ACCESS_TOKEN -> SocialType.NAVER
        LoginType.GOOGLE_ID_TOKEN -> SocialType.GOOGLE
        LoginType.APPLE_ID_TOKEN -> SocialType.APPLE
        LoginType.REFRESH -> throw UnsupportedOperationException()
    }
