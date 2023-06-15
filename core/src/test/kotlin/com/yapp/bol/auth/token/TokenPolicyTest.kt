package com.yapp.bol.auth.token

import com.yapp.bol.auth.Token
import com.yapp.bol.auth.UserId
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import java.time.Duration
import java.time.LocalDateTime

class TokenPolicyTest : FunSpec() {
    private val tokenUtils: TokenUtils = mockk()

    init {
        test("TokenPolicy.Generate 테스트") {
            // given
            val userId = UserId(123L)
            val now = LocalDateTime.of(1970, 1, 1, 0, 0, 0)
            val expireDuration = Duration.ofDays(7)
            val expiredAt = now.plus(expireDuration)
            val mockToken = Token("value", userId, expiredAt)

            mockkStatic(LocalDateTime::class)
            every { LocalDateTime.now() } returns now
            every { tokenUtils.generate(userId, expiredAt) } returns mockToken

            // when
            val policy = TokenPolicy(tokenUtils, expireDuration)
            val result = policy.generate(userId)

            // then
            verify(exactly = 1) { tokenUtils.generate(userId, now.plus(expireDuration)) }
            result shouldBe mockToken
        }
    }
}
