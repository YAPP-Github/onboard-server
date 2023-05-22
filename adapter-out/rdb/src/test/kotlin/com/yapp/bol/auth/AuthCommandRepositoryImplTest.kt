package com.yapp.bol.auth

import com.yapp.bol.user.UserEntity
import com.yapp.bol.user.UserRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class AuthCommandRepositoryImplTest : FunSpec() {
    private val authSocialRepository: AuthSocialRepository = mockk()
    private val userRepository: UserRepository = mockk()

    private val sut = AuthCommandRepositoryImpl(authSocialRepository, userRepository)

    init {
        test("registerUserTest") {
            // given
            val loginType = LoginType.KAKAO
            val socialId = "kakaoID"
            val user = UserEntity.of(1234L)

            every { userRepository.save(any()) } returns user
            every { authSocialRepository.save(any()) } returns AuthSocialEntity(
                loginType.toSocialType(),
                socialId,
                user.id
            )

            // when
            val result = sut.registerUser(loginType, socialId)

            // then
            verify { userRepository.save(any()) }
            verify { authSocialRepository.save(any()) }
            result.id shouldBe user.id
        }
    }
}
