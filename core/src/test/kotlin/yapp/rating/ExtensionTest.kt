package yapp.rating

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime
import java.util.Calendar
import java.util.GregorianCalendar

class ExtensionTest : FunSpec() {
    init {
        context("LocalDateTime.toDate() 테스트") {
            forAll(
                row(2023, 5, 6, 8, 28),
                row(2023, 1, 1, 0, 0),
                row(2023, 12, 31, 0, 0),
                row(2023, 12, 31, 23, 59),
            ) { year, month, day, hour, minute ->
                test("$year-$month-$day $hour:$minute") {
                    val localDateTime = LocalDateTime.of(year, month, day, hour, minute)

                    val date = localDateTime.toDate()
                    val calendar: Calendar = GregorianCalendar()
                    calendar.time = date

                    calendar.get(Calendar.YEAR) shouldBe year
                    calendar.get(Calendar.MONTH) + 1 shouldBe month
                    calendar.get(Calendar.DAY_OF_MONTH) shouldBe day
                }
            }
        }
    }
}
