package com.yapp.bol.social

import com.yapp.bol.auth.LoginType
import com.yapp.bol.auth.SocialUser
import com.yapp.bol.auth.social.SocialLoginClient
import com.yapp.bol.social.apple.AppleIdTokenLoginClient
import com.yapp.bol.social.google.GoogleIdTokenLoginClient
import com.yapp.bol.social.kakao.KakaoAccessTokenLoginClient
import com.yapp.bol.social.naver.NaverAccessTokenLoginClient
import org.springframework.stereotype.Component

@Component
internal class SocialLoginClientFacade(
    private val naverSocialLoginClient: NaverAccessTokenLoginClient,
    private val kakaoSocialLoginClient: KakaoAccessTokenLoginClient,
    private val googleIdTokenLoginClient: GoogleIdTokenLoginClient,
    private val appleIdTokenLoginClient: AppleIdTokenLoginClient,
) : SocialLoginClient {

    override fun login(loginType: LoginType, token: String): SocialUser =
        when (loginType) {
            LoginType.NAVER_ACCESS_TOKEN -> naverSocialLoginClient.login(token)
            LoginType.KAKAO_ACCESS_TOKEN -> kakaoSocialLoginClient.login(token)
            LoginType.GOOGLE_ID_TOKEN -> googleIdTokenLoginClient.login(token)
            LoginType.APPLE_ID_TOKEN -> appleIdTokenLoginClient.login(token)
            LoginType.REFRESH -> throw UnsupportedOperationException()
        }
}
