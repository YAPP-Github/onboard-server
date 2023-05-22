package yapp.rating.social

import org.springframework.stereotype.Component
import yapp.rating.auth.LoginFailedException
import yapp.rating.auth.SocialUser

@Component
internal class MockSocialClient {
    fun login(token: String): SocialUser {
        if (token.startsWith("SUCCESS")) {
            return SocialUser(token.substring("SUCCESS".length))
        }

        throw LoginFailedException()
    }
}
