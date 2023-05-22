package com.yapp.bol.social.naver

import com.yapp.bol.SocialLoginFailedException
import com.yapp.bol.auth.SocialUser
import org.springframework.stereotype.Component

@Component
internal class NaverAccessTokenLoginClient(
    private val naverAuthClient: NaverOpenApiClient,
) {
    fun login(token: String): SocialUser {
        val userResponse = naverAuthClient.getUserProfile("Bearer $token").block()
            ?: throw SocialLoginFailedException()

        if (userResponse.resultCode == "00") return userResponse.userInfo

        throw SocialLoginFailedException()
    }
}
