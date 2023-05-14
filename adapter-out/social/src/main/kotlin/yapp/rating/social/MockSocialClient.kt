package yapp.rating.social

import org.springframework.stereotype.Component
import yapp.rating.auth.LoginFailedException
import yapp.rating.auth.LoginType
import yapp.rating.auth.SocialUser
import yapp.rating.auth.social.SocialLoginClient

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
