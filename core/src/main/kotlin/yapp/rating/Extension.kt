package yapp.rating

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

fun LocalDateTime.toDate(): Date {
    val instant = this.atZone(ZoneId.systemDefault()).toInstant()
    return Date.from(instant)
}
