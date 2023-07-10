package com.yapp.bol.terms.dto

import com.yapp.bol.terms.Terms
import com.yapp.bol.terms.TermsCode

data class TermsResponse(
    val contents: List<TermsItemResponse>
)

data class TermsItemResponse(
    val code: TermsCode,
    val title: String,
    val url: String,
    val isOptional: Boolean,
)

fun Terms.toResponse(): TermsItemResponse = TermsItemResponse(
    code = this.code,
    title = this.title,
    url = this.url,
    isOptional = this.isOptional
)
