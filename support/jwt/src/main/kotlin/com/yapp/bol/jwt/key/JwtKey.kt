package com.yapp.bol.jwt.key

import java.security.Key
import java.security.PublicKey

interface JwtKey {
    fun getKey(): Key
}

fun JwtKey(byteArray: ByteArray): JwtKey {
    return JwtByteArrayKey(byteArray)
}

fun JwtKey(n: String, e: String, keyType: String): JwtKey {
    return JwtRsaKey(n, e, keyType)
}
