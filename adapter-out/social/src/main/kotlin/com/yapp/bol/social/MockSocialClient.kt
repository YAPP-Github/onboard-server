package com.yapp.bol.social

import com.yapp.bol.auth.LoginFailedException
import com.yapp.bol.auth.LoginType
import com.yapp.bol.auth.SocialUser
import com.yapp.bol.auth.social.SocialLoginClient
import org.springframework.stereotype.Component

@Component
class MockSocialClient : SocialLoginClient {
    override fun isSupport(socialType: LoginType): Boolean = true

    override fun login(token: String): SocialUser {
        if (token.startsWith("SUCCESS")) {
            return SocialUser(token.substring("SUCCESS".length))
        }

        throw LoginFailedException()
    }
}
