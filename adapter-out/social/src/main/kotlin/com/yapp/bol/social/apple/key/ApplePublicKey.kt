package com.yapp.bol.social.apple.key

import com.yapp.bol.jwt.key.JwtKey

data class ApplePublicKeysResponse(
    val keys: List<ApplePublicKey>
) {
    operator fun get(kid: String, alg: String): ApplePublicKey? =
        keys.firstOrNull {
            it.kid == kid && it.alg == alg
        }
}

data class ApplePublicKey(
    val kty: String,
    val kid: String,
    val alg: String,
    val n: String,
    val e: String,
) {
    fun toJwtKey(): JwtKey {
        return JwtKey(n, e, kty)
    }
}
