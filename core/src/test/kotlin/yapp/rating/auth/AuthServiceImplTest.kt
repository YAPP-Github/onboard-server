package yapp.rating.auth

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.time.LocalDateTime
import yapp.rating.SocialLoginException
import yapp.rating.auth.social.SocialLoginClient

class AuthServiceImplTest : FunSpec() {
    private val socialLoginClient: SocialLoginClient = mockk()
    private val authCommandRepository: AuthCommandRepository = mockk()
    private val authQueryRepository: AuthQueryRepository = mockk()
    private val tokenService: TokenService = mockk()

    private val sut = AuthServiceImpl(
        listOf(socialLoginClient),
        authCommandRepository,
        authQueryRepository,
        tokenService,
    )

    override fun isolationMode() = IsolationMode.InstancePerTest

    init {
        val userId = 123L
        val accessTokenMock = Token("accessToken", userId, LocalDateTime.now())
        val refreshTokenMock = Token("refreshToken", userId, LocalDateTime.now())

        context("소셜 로그인 성공") {
            // given
            val loginType = LoginType.KAKAO
            val kakaoToken = "KAKAO_TOKEN"
            val kakaoUserId = "KAKAO_USER_ID"

            every { socialLoginClient.isSupport(loginType) } returns true
            every { socialLoginClient.login(kakaoToken) } returns SocialUser(kakaoUserId)

            every { tokenService.generateAccessToken(userId) } returns accessTokenMock
            every { tokenService.generateRefreshToken(userId) } returns refreshTokenMock

            test("기존 회원") {
                // given
                every { authQueryRepository.findAuthUser(loginType, kakaoUserId) } returns AuthUser(userId)

                // when
                val result = sut.login(loginType, kakaoToken)

                // then
                verify(exactly = 0) { authCommandRepository.registerUser(any(), any()) }
                result.accessToken shouldBe accessTokenMock
                result.refreshToken shouldBe refreshTokenMock
            }

            test("신규 회원") {
                // given
                every { authQueryRepository.findAuthUser(loginType, kakaoUserId) } returns null
                every { authCommandRepository.registerUser(loginType, kakaoUserId) } returns AuthUser(userId)

                // when
                val result = sut.login(loginType, kakaoToken)

                // then
                verify(exactly = 1) { authCommandRepository.registerUser(loginType, kakaoUserId) }
                result.accessToken shouldBe accessTokenMock
                result.refreshToken shouldBe refreshTokenMock
            }
        }

        test("소셜 로그인 실패") {
            // given
            val loginType = LoginType.KAKAO
            val kakaoToken = "KAKAO_TOKEN"

            every { socialLoginClient.isSupport(loginType) } returns true
            every { socialLoginClient.login(kakaoToken) } throws SocialLoginException()

            // when & then
            shouldThrow<SocialLoginException> {
                sut.login(loginType, kakaoToken)
            }
            verify(exactly = 0) { authCommandRepository.registerUser(any(), any()) }
            verify(exactly = 0) { authQueryRepository.findAuthUser(any(), any()) }
            verify(exactly = 0) { tokenService.generateAccessToken(any()) }
            verify(exactly = 0) { tokenService.generateRefreshToken(any()) }
        }
    }
}
