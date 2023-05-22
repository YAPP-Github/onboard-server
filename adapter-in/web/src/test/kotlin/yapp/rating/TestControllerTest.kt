package yapp.rating

import yapp.rating.base.ControllerTest
import yapp.rating.base.NUMBER
import yapp.rating.base.OpenApiTag
import yapp.rating.base.STRING

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
