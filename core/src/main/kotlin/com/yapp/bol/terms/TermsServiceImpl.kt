package com.yapp.bol.terms

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class TermsServiceImpl(
    @Value("\${bol.server.host}") private val host: String,
) {
    private val termsCache = HashMap<TermsCode, Terms>()

    private val wholeTerms: List<Terms> by lazy {
        TermsCode.values().toList().map { getTerms(it) }
    }

    private fun getTerms(code: TermsCode): Terms {
        return termsCache[code] ?: code.toDomain()
    }
    private fun TermsCode.toDomain(): Terms =
        Terms(
            code = this,
            url = "$host/${this.path}"
        )
}
