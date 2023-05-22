package com.yapp.bol

import com.yapp.bol.base.ControllerTest
import com.yapp.bol.base.NUMBER
import com.yapp.bol.base.OpenApiTag
import com.yapp.bol.base.STRING

class TestControllerTest : ControllerTest() {
    override val controller = TestController()

    init {
        test("Get Test") {
            get("/v1/test") {}
                .isStatus(200)
                .makeDocument(
                    DocumentInfo(identifier = "test", tag = OpenApiTag.TEST),
                    responseFields(
                        "value" type STRING means "English??",
                        "test" type NUMBER means "이건 몬지몰라" isOptional true
                    )
                )
        }
    }
}
