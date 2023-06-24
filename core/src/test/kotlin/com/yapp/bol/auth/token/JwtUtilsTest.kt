package com.yapp.bol.auth.token

import com.yapp.bol.auth.UserId
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.security.SignatureException
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime
import java.util.Base64

class JwtUtilsTest : FunSpec() {
    private val sut = JwtUtils(ByteArray(64) { 0 })

    init {
        val userId = UserId(1L)
        val expiredTime = LocalDateTime.of(2000, 1, 1, 0, 0)

        test("generate") {
            // when
            val result = sut.generate(userId, expiredTime)

            // then
            result.userId shouldBe userId
            result.expiredAt shouldBe expiredTime

            shouldNotThrow<Exception> {
                val jwt = result.value.split(".")
                Base64.getDecoder().decode(jwt[0])
                Base64.getDecoder().decode(jwt[1])
            }
        }

        context("validate") {
            test("유효한 토큰") {
                // when
                val result = sut.validate(JWT)

                // then
                result shouldBe true
            }
            test("유효하지 않는 토큰") {
                // given
                val token = JWT + "A"

                // when
                val result = sut.validate(token)

                // then
                result shouldBe false
            }
            test("만료된 토큰") {
                // when
                val result = sut.validate(EXPIRED_JWT)

                // then
                result shouldBe false
            }
        }

        context("getUserId") {
            test("유효한 토큰") {
                // when
                val result = sut.getUserId(JWT)

                // then
                result shouldBe 1L
            }
            test("유효하지 않는 토큰") {
                shouldThrow<SignatureException> { sut.getUserId(INVALID_JWT) }
            }
            test("만료된 토큰") {
                // when & then
                shouldThrow<ExpiredJwtException> { sut.getUserId(EXPIRED_JWT) }
            }
        }
    }

    companion object {
        // 100년 짜리 토큰 (2123년)
        private const val JWT =
            "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwiZXhwIjo0ODM1MjUyNzA0fQ" +
                ".yrR0wq7_C4LCLzS7yJBQVsAT45F-QzKhLvI3eC88G3-eX9YXRZFnVWQFLUPCYgF6XVay0hO8y1IaZYTYeJe7SQ"
        private const val EXPIRED_JWT =
            "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwiZXhwIjo5NDY2NTI0MDB9" +
                ".uEBGh5aKvtvVpz66YSCXMQ4wlgMH7Gan0LDIvm9B5JgQXYHI4Jnq0gXoPQ_4ISuhl761fOKP_70sbNUxNpxUJg"
        private const val INVALID_JWT = JWT + "A"
    }
}
