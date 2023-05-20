package com.yapp.bol.social.naver

import com.yapp.bol.SocialLoginFailedException
import com.yapp.bol.auth.LoginType
import com.yapp.bol.auth.SocialUser
import com.yapp.bol.auth.social.SocialLoginClient
import org.springframework.stereotype.Component

@Component
internal class NaverLoginClient(
    private val naverAuthClient: NaverOpenApiClient,
) : SocialLoginClient {
    override fun isSupport(socialType: LoginType): Boolean =
        socialType == LoginType.NAVER

    override fun login(token: String): SocialUser {
        val userResponse = naverAuthClient.getUserProfile("Bearer $token").block()
            ?: throw SocialLoginFailedException()

        if(userResponse.resultCode == "00") return userResponse.userInfo

        throw SocialLoginFailedException()
    }
}
