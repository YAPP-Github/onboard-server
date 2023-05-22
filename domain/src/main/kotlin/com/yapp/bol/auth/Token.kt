package com.yapp.bol.auth

import com.yapp.bol.ExpiredTokenException
import com.yapp.bol.InvalidTokenException
import java.time.LocalDateTime
import java.util.Base64

data class Token(
    val value: String,
    val userId: Long,
    val expiredAt: LocalDateTime,
) {
    val isExpired: Boolean
        get() = expiredAt.isBefore(LocalDateTime.now())

    fun toBinary(): ByteArray = Base64.getDecoder().decode(value)

}

fun Token?.validate(): Token {
    if (this == null) throw InvalidTokenException
    if (this.isExpired) throw ExpiredTokenException
    return this
}
