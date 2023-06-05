package com.yapp.bol.social.google

import com.yapp.bol.SocialLoginFailedException
import com.yapp.bol.auth.SocialUser
import org.springframework.stereotype.Component

@Component
internal class GoogleIdTokenLoginClient(
    private val idTokenUtils: IdTokenUtils,
) {
    fun login(token: String): SocialUser {
        val sut = idTokenUtils.getSub(token) ?: throw SocialLoginFailedException()

        return GoogleSocialUser(id = sut)
    }
}
