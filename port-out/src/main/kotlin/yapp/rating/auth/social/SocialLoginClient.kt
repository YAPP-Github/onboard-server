package yapp.rating.auth.social

import yapp.rating.auth.LoginType
import yapp.rating.auth.SocialUser

interface SocialLoginClient {
    fun login(loginType: LoginType, token: String): SocialUser
}
