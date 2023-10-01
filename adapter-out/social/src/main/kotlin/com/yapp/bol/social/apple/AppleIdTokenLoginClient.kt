package com.yapp.bol.social.apple

import com.yapp.bol.SocialLoginFailedException
import com.yapp.bol.auth.SocialUser
import com.yapp.bol.jwt.JwtUtils
import com.yapp.bol.jwt.key.JwtKey
import com.yapp.bol.social.apple.key.AppleApiClient
import org.springframework.stereotype.Component

@Component
class AppleIdTokenLoginClient(
    private val jwtUtils: JwtUtils,
    private val appleApiClient: AppleApiClient,
) {
    fun login(token: String): SocialUser {
        val publicKeys = appleApiClient.getPublicKeys().block() ?: throw SocialLoginFailedException()
        val header = jwtUtils.getHeader(token)
        val kid = header["kid"] ?: throw SocialLoginFailedException()
        val alg = header["alg"] ?: throw SocialLoginFailedException()

        val key = publicKeys[kid, alg]?.toJwtKey() ?: throw SocialLoginFailedException()

        val payload = validateAndGetPayload(token, key) ?: throw SocialLoginFailedException()

        return AppleSocialUser(payload.sub)
    }

    private fun validateAndGetPayload(token: String, key: JwtKey): AppleIdTokenPayload? {
        if (jwtUtils.validate(token, key).not()) return null

        val payload = jwtUtils.getPayload<AppleIdTokenPayload>(token)

        if (payload.iss != APPLE_ISS || payload.aud != APPLE_AUD) return null

        return payload
    }

    companion object {
        private const val APPLE_ISS = "https://appleid.apple.com"
        private const val APPLE_AUD = "onboard.onboard-iOS"
    }
}
