package com.yapp.bol.terms

import com.yapp.bol.auth.UserId
import com.yapp.bol.base.ARRAY
import com.yapp.bol.base.BOOLEAN
import com.yapp.bol.base.ControllerTest
import com.yapp.bol.base.OpenApiTag
import com.yapp.bol.base.STRING
import com.yapp.bol.terms.dto.AgreeTermsRequest
import io.mockk.every
import io.mockk.mockk

class TermsControllerTest : ControllerTest() {
    private val termsService: TermsService = mockk()
    override val controller = TermsController("http://localhost:8080/", termsService)

    init {
        test("이용 약관 가져오기") {
            val userId = UserId(234L)
            every { termsService.getTermsList(userId) } returns listOf(TermsCode.SERVICE_V1, TermsCode.PRIVACY_V1)

            get("/v1/terms") {
                authorizationHeader(userId)
            }
                .isStatus(200)
                .makeDocument(
                    DocumentInfo(
                        identifier = "terms/{method-name}",
                        description = "동의가 필요한 이용약관 가져오기",
                        tag = OpenApiTag.TERMS,
                    ),
                    responseFields(
                        "contents" type ARRAY means "약관 목록",
                        "contents[].code" type STRING means "약관 Id",
                        "contents[].title" type STRING means "약관 제목",
                        "contents[].isRequired" type BOOLEAN means "약관 필수 여부",
                        "contents[].url" type STRING means "약관 내용 url",
                    )
                )
        }

        test("이용 약관 동의하기") {
            val userId = UserId(234L)
            val request = AgreeTermsRequest(terms = listOf(TermsCode.SERVICE_V1, TermsCode.PRIVACY_V1))
            every { termsService.agreeTerms(userId, request.terms) } returns Unit

            post("/v1/terms", request) {
                authorizationHeader(userId)
            }
                .isStatus(200)
                .makeDocument(
                    DocumentInfo(
                        identifier = "terms/{method-name}",
                        description = "이용약관 동의하기",
                        tag = OpenApiTag.TERMS,
                    ),
                    requestFields(
                        "terms" type ARRAY means "동의할 약관 Code 목록",
                    )
                )
        }
    }
}
