package com.yapp.bol.terms

import com.yapp.bol.auth.UserId
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class TermsServiceImpl(
    @Value("\${bol.server.host}") private val host: String,
    private val termsCommandRepository: TermsCommandRepository,
) : TermsService {
    private val termsCache = HashMap<TermsCode, Terms>()

    private fun getTerms(code: TermsCode): Terms {
        return termsCache[code] ?: code.toDomain()
    }

    private val wholeTerms: List<Terms> by lazy {
        TermsCode.values().toList().map { getTerms(it) }
    }

    override fun getTermsList(): List<Terms> {
        return wholeTerms
    }

    override fun agreeTerms(userId: UserId, termsCode: TermsCode) {
        termsCommandRepository.agreeTerms(userId, termsCode)
    }

    private fun TermsCode.toDomain(): Terms =
        Terms(
            code = this,
            url = "$host/${this.path}"
        )
}
