package com.yapp.bol.validate

import com.yapp.bol.InvalidNicknameException
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec

class NicknameValidatorTest : FunSpec() {
    init {
        test("SUCCESS") {
            for (i in 1..10) {
                val nickname = "x".repeat(i)
                shouldNotThrow<Exception> {
                    NicknameValidator.validate(nickname)
                }
            }
        }

        test("FAIL") {
            for (i in listOf(0, 11)) {
                val nickname = "x".repeat(i)
                shouldThrow<InvalidNicknameException> {
                    NicknameValidator.validate(nickname)
                }
            }
        }
    }
}
