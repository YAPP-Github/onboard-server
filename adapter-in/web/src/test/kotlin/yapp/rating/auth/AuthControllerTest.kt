package yapp.rating.auth

import io.mockk.every
import io.mockk.mockk
import java.time.LocalDateTime
import yapp.rating.auth.dto.LoginRequest
import yapp.rating.base.ControllerTest
import yapp.rating.base.ENUM
import yapp.rating.base.OpenApiTag
import yapp.rating.base.STRING

class AuthControllerTest : ControllerTest() {
    private val authService: AuthService = mockk()
    override val controller = AuthController(authService)

    init {
        test("POST /v1/auth/login") {
            val request = LoginRequest(LoginType.KAKAO, "Token")
            val authToken = AuthToken(
                Token("ACCESS_TOKEN", 123L, LocalDateTime.now()),
                Token("ACCESS_TOKEN", 123L, LocalDateTime.now())
            )
            every { authService.login(any(), any()) } returns authToken

            post("/v1/auth/login", request) {}
                .isStatus(200)
                .makeDocument(
                    DocumentInfo(identifier = "test", tag = OpenApiTag.AUTH),
                    requestFields(
                        "type" type ENUM(LoginType::class) means "로그인 방법",
                        "token" type STRING means "로그인에 사용되는 토큰",
                    ),
                    responseFields(
                        "accessToken" type STRING means "엑세스 토큰",
                        "refreshToken" type STRING means "Refresh 토큰" isOptional true,
                    )
                )
        }
    }
}
