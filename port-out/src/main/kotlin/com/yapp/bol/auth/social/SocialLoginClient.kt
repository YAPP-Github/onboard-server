package com.yapp.bol.auth.social

import com.yapp.bol.auth.LoginType
import com.yapp.bol.auth.SocialUser

interface SocialLoginClient {
    fun isSupport(socialType: LoginType): Boolean
    fun login(token: String): SocialUser
}
