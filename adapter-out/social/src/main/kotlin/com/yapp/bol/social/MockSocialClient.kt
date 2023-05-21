package com.yapp.bol.social

import com.yapp.bol.auth.LoginFailedException
import com.yapp.bol.auth.LoginType
import com.yapp.bol.auth.SocialUser
import com.yapp.bol.auth.social.SocialLoginClient
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Order(value = Ordered.LOWEST_PRECEDENCE)
@Component
class MockSocialClient : SocialLoginClient {
    override fun isSupport(socialType: LoginType): Boolean = socialType != LoginType.NAVER_ACCESS_TOKEN

    override fun login(token: String): SocialUser {
        if (token.startsWith("SUCCESS")) {
            return object : SocialUser {
                override val id: String
                    get() = token.substring("SUCCESS".length)
            }
        }

        throw LoginFailedException()
    }
}
