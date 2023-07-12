package com.yapp.bol.terms

import com.yapp.bol.NotExistRequiredTermsException
import com.yapp.bol.OldVersionTermsException
import com.yapp.bol.auth.UserId
import org.springframework.stereotype.Service

@Service
class TermsServiceImpl(
    private val termsQueryRepository: TermsQueryRepository,
    private val termsCommandRepository: TermsCommandRepository,
) : TermsService {

    private val wholeTerms: List<TermsCode> = TermsCode.latestTerms()

    override fun getTermsList(userId: UserId): List<TermsCode> {
        val list = termsQueryRepository.getAgreedTermsByUserId(userId)

        return wholeTerms.filter { list.contains(it).not() }
    }

    override fun agreeTerms(userId: UserId, termsCode: List<TermsCode>) {
        if (termsCode.any { it.nextVersion != null }) throw OldVersionTermsException

        termsCommandRepository.agreeTerms(userId, termsCode)

        if (getTermsList(userId).any { it.isRequired }) throw NotExistRequiredTermsException
    }
}
