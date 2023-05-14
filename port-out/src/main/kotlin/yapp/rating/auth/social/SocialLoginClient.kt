package yapp.rating.auth.social

import yapp.rating.auth.LoginType
import yapp.rating.auth.SocialUser

interface SocialLoginClient {
    fun isSupport(socialType: LoginType): Boolean
    fun login(token: String): SocialUser
}
