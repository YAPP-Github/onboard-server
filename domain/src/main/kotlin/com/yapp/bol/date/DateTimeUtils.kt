package com.yapp.bol.date

import com.yapp.bol.InvalidDateTimeException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object DateTimeUtils {
    private const val DATE_TIME_FORMAT = "yy/MM/dd HH:mm"
    private val formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)

    fun parseString(date: String): LocalDateTime {
        return try {
            LocalDateTime.parse(date, formatter)
        } catch (e: DateTimeParseException) {
            throw InvalidDateTimeException
        }
    }
}
