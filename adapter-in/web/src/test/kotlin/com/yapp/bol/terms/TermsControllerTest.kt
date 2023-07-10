package com.yapp.bol.terms

import com.yapp.bol.base.ARRAY
import com.yapp.bol.base.ControllerTest
import com.yapp.bol.base.OpenApiTag
import com.yapp.bol.base.STRING
import io.mockk.every
import io.mockk.mockk

class TermsControllerTest : ControllerTest() {
    private val termsService: TermsService = mockk()
    override val controller = TermsController(termsService)

    init {
        test("이용 약관 가져오기") {
            every { termsService.getTermsList() } returns listOf(
                Terms(TermsCode.SERVICE_V1, "http://localhost:8080/${TermsCode.SERVICE_V1.path}"),
                Terms(TermsCode.PRIVACY_V1, "http://localhost:8080/${TermsCode.PRIVACY_V1.path}"),
            )

            get("/v1/terms") {}
                .isStatus(200)
                .makeDocument(
                    DocumentInfo(identifier = "test", tag = OpenApiTag.TERMS),
                    responseFields(
                        "contents" type ARRAY means "약관 목록",
                        "contents[].code" type STRING means "약관 Id",
                        "contents[].title" type STRING means "약관 제목",
                        "contents[].isOptional" type STRING means "약관 필수 여부",
                        "contents[].url" type STRING means "약관 내용 url",
                    )
                )
        }
    }
}
