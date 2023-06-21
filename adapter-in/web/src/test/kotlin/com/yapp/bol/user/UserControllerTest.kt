package com.yapp.bol.user

import com.yapp.bol.NotFoundUserException
import com.yapp.bol.auth.UserId
import com.yapp.bol.base.ControllerTest
import com.yapp.bol.base.NUMBER
import com.yapp.bol.base.OpenApiTag
import com.yapp.bol.base.STRING
import io.mockk.every
import io.mockk.mockk

class UserControllerTest : ControllerTest() {
    private val userService: UserService = mockk()
    override val controller: Any
        get() = UserController(userService)

    init {
        test("내 기본 정보 가져오기") {
            val user = User(
                id = UserId(2220),
                nickname = "닉네임",
            )
            every { userService.getUser(user.id) } returns user

            get("/v1/user/me") {
                authorizationHeader(user.id)
            }
                .isStatus(200)
                .makeDocument(
                    DocumentInfo(identifier = "user/{method-name}", tag = OpenApiTag.USER),
                    responseFields(
                        "id" type NUMBER means "유저 아이디",
                        "nickname" type STRING means "유저 기본 닉네임" isOptional true,
                    )
                )
        }
    }
}
