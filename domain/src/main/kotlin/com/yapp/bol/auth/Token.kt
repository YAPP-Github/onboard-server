package com.yapp.bol.auth

import com.yapp.bol.ExpiredTokenException
import com.yapp.bol.InvalidTokenException
import java.time.LocalDateTime
import java.util.Base64

data class Token(
    val value: String,
    val userId: UserId,
    val expiredAt: LocalDateTime,
) {
    constructor(value: ByteArray, userId: UserId, expiredAt: LocalDateTime) : this(
        Base64.getEncoder().encodeToString(value),
        userId,
        expiredAt,
    )

    val isExpired: Boolean
        get() = expiredAt.isBefore(LocalDateTime.now())

    fun toBinary(): ByteArray = value.toBinary()
}

fun Token?.validate(): Token {
    if (this == null) throw InvalidTokenException
    if (this.isExpired) throw ExpiredTokenException
    return this
}

fun String.toBinary(): ByteArray {
    return Base64.getDecoder().decode(this)
}
