package com.yapp.bol.setting

import com.yapp.bol.auth.UserId
import com.yapp.bol.base.ARRAY
import com.yapp.bol.base.BOOLEAN
import com.yapp.bol.base.ControllerTest
import com.yapp.bol.base.OpenApiTag
import com.yapp.bol.base.STRING
import com.yapp.bol.terms.TermsCode
import com.yapp.bol.terms.TermsController
import com.yapp.bol.terms.TermsService
import io.mockk.every
import io.mockk.mockk

class SettingControllerTest : ControllerTest() {
    private val termsService: TermsService = mockk()
    override val controller = TermsController("http://localhost:8080/", termsService)

    init {
        test("설정에서 이용 약관 가져오기") {
            every { termsService.getWholeTerms() } returns listOf(TermsCode.SERVICE_V1, TermsCode.PRIVACY_V1)

            get("/v1/setting") {}
                .isStatus(200)
                .makeDocument(
                    DocumentInfo(
                        identifier = "terms/{method-name}",
                        description = "설정 화면에서 보여줄 이용약관 가져오기",
                        tag = OpenApiTag.SETTING,
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
    }
}
