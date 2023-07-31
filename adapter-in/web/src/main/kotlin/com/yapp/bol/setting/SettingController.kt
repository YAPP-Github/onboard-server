package com.yapp.bol.setting

import com.yapp.bol.terms.TermsService
import com.yapp.bol.terms.dto.TermsResponse
import com.yapp.bol.terms.dto.toResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/setting")
class SettingController(
    @Value("\${bol.server.host}") private val host: String,
    private val termsService: TermsService,
) {
    @GetMapping("/terms")
    fun getSetting(): TermsResponse {
        return TermsResponse(termsService.getWholeTerms().map { it.toResponse(host) })
    }
}
