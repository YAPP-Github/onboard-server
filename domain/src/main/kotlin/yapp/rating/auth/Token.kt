package yapp.rating.auth

import java.time.LocalDateTime

data class Token(
    val value: String,
    val userId: Long,
    val expiredAt: LocalDateTime
)
