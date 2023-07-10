package com.yapp.bol.terms

import com.yapp.bol.auth.UserId

interface TermsCommandRepository {
    fun agreeTerms(userId: UserId, termsCode: TermsCode)
}
