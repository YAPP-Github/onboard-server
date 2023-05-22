package com.yapp.bol.auth

import java.time.LocalDateTime
import java.util.Base64

data class Token(
    val value: String,
    val userId: Long,
    val expiredAt: LocalDateTime,
) {
    fun toBinary(): ByteArray = Base64.getDecoder().decode(value)
}
