package com.yapp.bol.auth

import com.yapp.bol.auth.token.TokenCommandRepository
import com.yapp.bol.auth.token.TokenPolicy
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import java.time.LocalDateTime

class TokenServiceTest : FunSpec() {
    private val tokenPolicy: TokenPolicy = mockk()
    private val tokenCommandRepository: TokenCommandRepository = mockk()

    private val sut = TokenService(tokenPolicy, tokenPolicy, tokenCommandRepository)

    init {
        context("Access Token") {
            test("generate - Success") {
                // given
                val userId = 123L
                val token = Token("value", userId, LocalDateTime.now())
                every { tokenPolicy.generate(userId) } returns token
                justRun { tokenCommandRepository.saveAccessToken(any()) }

                // when
                val result = sut.generateAccessToken(userId)

                // then
                result shouldBe token
                verify(exactly = 1) { tokenCommandRepository.saveAccessToken(token) }
            }
        }

        context("Refresh Token") {
            test("generate - Success") {
                // given
                val userId = 123L
                val token = Token("value", userId, LocalDateTime.now())
                every { tokenPolicy.generate(userId) } returns token
                justRun { tokenCommandRepository.saveRefreshToken(any()) }

                // when
                val result = sut.generateRefreshToken(userId)

                // then
                result shouldBe token
                verify(exactly = 1) { tokenCommandRepository.saveRefreshToken(token) }
            }
        }
    }
}
