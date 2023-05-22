package com.yapp.bol.auth.social

import com.yapp.bol.auth.LoginType
import com.yapp.bol.auth.SocialUser

interface SocialLoginClient {
    fun login(loginType: LoginType, token: String): SocialUser
}
