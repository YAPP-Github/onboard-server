package com.yapp.bol.social

import com.yapp.bol.auth.LoginType
import com.yapp.bol.auth.SocialUser
import com.yapp.bol.auth.social.SocialLoginClient
import org.springframework.stereotype.Component

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
