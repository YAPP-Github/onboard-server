package com.yapp.bol.terms

import com.yapp.bol.auth.UserId

interface TermsService {
    fun getTermsList(userId: UserId): List<TermsCode>

    fun agreeTerms(userId: UserId, termsCode: List<TermsCode>)
}
