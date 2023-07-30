package com.yapp.bol.validate

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class NicknameValidatorTest : FunSpec() {
    init {
        test("길이 SUCCESS") {
            for (i in 1..10) {
                val nickname = "x".repeat(i)

                NicknameValidator.validate(nickname) shouldBe true
            }
        }

        test("특수문자 SUCCESS") {
            val nickname = "s12345_"

            NicknameValidator.validate(nickname) shouldBe true
        }

        test("길이 FAIL") {
            for (i in listOf(0, 11)) {
                val nickname = "x".repeat(i)

                NicknameValidator.validate(nickname) shouldBe false
            }
        }

        test("특수문자 FAIL") {
            val nickname = "s12345!@#"

            NicknameValidator.validate(nickname) shouldBe false
        }
    }
}
