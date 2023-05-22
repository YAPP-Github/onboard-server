package com.yapp.bol.social

import com.yapp.bol.auth.LoginFailedException
import com.yapp.bol.auth.SocialUser
import org.springframework.stereotype.Component

@Component
internal class MockSocialClient {
    fun login(token: String): SocialUser {
        if (token.startsWith("SUCCESS")) {
            return object : SocialUser{
                override val id: String
                    get() = token.substring("SUCCESS".length)
            }
        }

        throw LoginFailedException()
    }
}
