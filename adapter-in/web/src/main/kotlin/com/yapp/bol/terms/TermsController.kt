package com.yapp.bol.terms

import com.yapp.bol.terms.dto.TermsResponse
import com.yapp.bol.terms.dto.toResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/terms")
class TermsController(
    private val termsService: TermsService,
) {

    @GetMapping
    fun getTerms(): TermsResponse {
        val list = termsService.getTermsList()
        return TermsResponse(
            list.map { it.toResponse() }
        )
    }
}
