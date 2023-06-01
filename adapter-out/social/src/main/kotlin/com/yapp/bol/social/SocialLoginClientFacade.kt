package com.yapp.bol.social

import com.yapp.bol.auth.LoginType
import com.yapp.bol.auth.SocialUser
import com.yapp.bol.auth.social.SocialLoginClient
import com.yapp.bol.social.kakao.KakaoAccessTokenLoginClient
import com.yapp.bol.social.naver.NaverAccessTokenLoginClient
import org.springframework.stereotype.Component

@Component
internal class SocialLoginClientFacade(
    private val mockSocialClient: MockSocialClient,
    private val naverSocialLoginClient: NaverAccessTokenLoginClient,
    private val kakaoSocialLoginClient: KakaoAccessTokenLoginClient,
) : SocialLoginClient {

    override fun login(loginType: LoginType, token: String): SocialUser =
        when (loginType) {
            LoginType.NAVER_ACCESS_TOKEN -> naverSocialLoginClient.login(token)
            LoginType.KAKAO_ACCESS_TOKEN -> kakaoSocialLoginClient.login(token)
            LoginType.GOOGLE -> mockSocialClient.login(token)
            LoginType.REFRESH -> throw UnsupportedOperationException()
        }
}
