package com.yapp.bol.social

import org.springframework.stereotype.Component
import yapp.rating.auth.LoginType
import yapp.rating.auth.SocialUser
import yapp.rating.auth.social.SocialLoginClient

@Component
internal class SocialLoginClientFacade(
    private val mockSocialClient: MockSocialClient,
) : SocialLoginClient {

    override fun login(loginType: LoginType, token: String): SocialUser =
        when (loginType) {
            LoginType.KAKAO, LoginType.NAVER, LoginType.GOOGLE -> mockSocialClient.login(token)
            LoginType.REFRESH -> throw UnsupportedOperationException()
        }
}
