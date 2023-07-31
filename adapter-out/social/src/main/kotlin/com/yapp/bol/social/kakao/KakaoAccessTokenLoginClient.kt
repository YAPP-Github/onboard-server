package com.yapp.bol.social.kakao

import com.yapp.bol.SocialLoginFailedException
import com.yapp.bol.auth.SocialUser
import org.springframework.stereotype.Component

@Component
internal class KakaoAccessTokenLoginClient(
    private val kakaoAuthClient: KakaoOpenApiClient,
) {
    fun login(token: String): SocialUser =
        kakaoAuthClient.getTokenInfo("Bearer $token").block()
            ?: throw SocialLoginFailedException()
}
