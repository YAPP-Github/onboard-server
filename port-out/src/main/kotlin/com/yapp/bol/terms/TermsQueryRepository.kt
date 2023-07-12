package com.yapp.bol.terms

import com.yapp.bol.auth.UserId

interface TermsQueryRepository {
    fun getAgreedTermsByUserId(userId: UserId): List<TermsCode>
}
