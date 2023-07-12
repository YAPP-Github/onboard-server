package com.yapp.bol.terms.dto

import com.yapp.bol.terms.TermsCode

data class AgreeTermsRequest(
    val terms: List<TermsCode>
)
