package yapp.rating.auth.token

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class OpaqueTokenUtilsTest : FunSpec() {
    init {
        val userId = 1L
        val expiredTime = LocalDateTime.now().plusDays(3)
        for (length in 20..200 step 4) {
            context("길이 : $length") {
                val sut = OpaqueTokenUtils(length)
                val token = getRandomString(length)
                test("generate") {
                    // when
                    val result = sut.generate(userId, expiredTime)

                    // then
                    result.value.length shouldBe length
                    result.userId shouldBe userId
                    result.expiredAt shouldBe expiredTime
                }
                test("validate") {
                    // when
                    val result = sut.validate(token)

                    // then
                    result shouldBe true
                }
                test("validate Fail length : ${length + 1}") {
                    // when
                    val result = sut.validate("$token=")

                    // then
                    result shouldBe false
                }
                test("getUserId") {
                    // when
                    val result = sut.getUserId(token)

                    // then
                    result shouldBe null
                }
            }
        }
    }

    private fun getRandomString(length: Int): String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }
}
