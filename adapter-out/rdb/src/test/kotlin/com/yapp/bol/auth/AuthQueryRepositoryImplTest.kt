package com.yapp.bol.auth

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.data.repository.findByIdOrNull

class AuthQueryRepositoryImplTest : FunSpec() {
    private val authSocialRepository: AuthSocialRepository = mockk()
    private val sut = AuthQueryRepositoryImpl(authSocialRepository)

    init {
        val loginType = LoginType.KAKAO_ACCESS_TOKEN
        val socialType = loginType.toSocialType()
        val socialId = "Kakao ID"
        val userId = 123L
        val authSocialEntity = AuthSocialEntity(socialType, socialId, userId)

        test("findAuthUser(id) - null") {
            // given
            every { authSocialRepository.findByUserId(userId) } returns null

            // when
            val result = sut.findAuthUser(userId)

            // then
            result shouldBe null
        }

        test("findAuthUser(id) - notNull") {
            // given
            every { authSocialRepository.findByUserId(userId) } returns authSocialEntity

            // when
            val result = sut.findAuthUser(userId)

            // then
            result shouldNotBe null
            result?.id shouldBe userId
        }

        test("findAuthUser(SocialType, SocialId) - null") {
            // given
            val socialInfo = SocialInfo(socialType, socialId)
            every { authSocialRepository.findByIdOrNull(socialInfo) } returns null

            // when
            val result = sut.findAuthUser(loginType, socialId)

            // then
            result shouldBe null
        }

        test("findAuthUser(SocialType, SocialId) - notNull") {
            // given
            val socialInfo = SocialInfo(socialType, socialId)
            every { authSocialRepository.findByIdOrNull(socialInfo) } returns authSocialEntity

            // when
            val result = sut.findAuthUser(loginType, socialId)

            // then
            result shouldNotBe null
            result?.id shouldBe userId
        }
    }
}
