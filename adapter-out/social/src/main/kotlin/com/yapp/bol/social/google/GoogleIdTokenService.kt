package com.yapp.bol.social.google

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.yapp.bol.SocialLoginFailedException
import java.security.GeneralSecurityException
import org.springframework.stereotype.Component

@Component
internal class GoogleIdTokenService(
    private val googleApiProperties: GoogleApiProperties,
) {
    private val idTokenVerifier =
        GoogleIdTokenVerifier.Builder(NetHttpTransport(), GsonFactory.getDefaultInstance())
            .setAudience(listOf(googleApiProperties.clientId))
            .build()

    fun getUserId(token: String): String? {
        try {
            val idToken = idTokenVerifier.verify(token)
            return idToken?.payload?.subject
        } catch (e: GeneralSecurityException) {
            e.printStackTrace()
            throw SocialLoginFailedException(e)
        }
    }
}
