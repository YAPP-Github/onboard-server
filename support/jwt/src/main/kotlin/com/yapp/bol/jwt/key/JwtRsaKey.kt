package com.yapp.bol.jwt.key

import java.math.BigInteger
import java.security.Key
import java.security.KeyFactory
import java.security.spec.RSAPublicKeySpec
import java.util.Base64

class JwtRsaKey(
    private val n: String,
    private val e: String,
    private val keyType: String,
) : JwtKey {

    override fun getKey(): Key {
        val nBytes: ByteArray = Base64.getUrlDecoder().decode(n)
        val eBytes: ByteArray = Base64.getUrlDecoder().decode(e)

        val n = BigInteger(1, nBytes)
        val e = BigInteger(1, eBytes)

        val publicKeySpec = RSAPublicKeySpec(n, e)
        val keyFactory: KeyFactory = KeyFactory.getInstance(keyType)
        return keyFactory.generatePublic(publicKeySpec)
    }
}
