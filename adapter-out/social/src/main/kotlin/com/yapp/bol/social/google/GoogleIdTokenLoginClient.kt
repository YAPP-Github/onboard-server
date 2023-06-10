package com.yapp.bol.social.google

import com.yapp.bol.SocialLoginFailedException
import com.yapp.bol.auth.SocialUser
import org.springframework.stereotype.Component

@Component
internal class GoogleIdTokenLoginClient(
    private val googleIdTokenService: GoogleIdTokenService,
) {
    fun login(token: String): SocialUser {
        val googleUserId = googleIdTokenService.getUserId(token) ?: throw SocialLoginFailedException()

        return GoogleSocialUser(id = googleUserId)
    }
}
