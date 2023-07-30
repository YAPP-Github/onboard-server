package com.yapp.bol.date

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DateTimeUtilsTest : FunSpec() {
    init {
        val dateTimeUtils = DateTimeUtils
        val dateTimeString = "21/10/10 10:10"

        test("날짜 문자열을 LocalDateTime 으로 변환한다") {
            val dateTime = dateTimeUtils.parseString(dateTimeString)

            dateTime.year shouldBe 2021
            dateTime.monthValue shouldBe 10
            dateTime.dayOfMonth shouldBe 10
            dateTime.hour shouldBe 10
            dateTime.minute shouldBe 10
        }
    }
}
