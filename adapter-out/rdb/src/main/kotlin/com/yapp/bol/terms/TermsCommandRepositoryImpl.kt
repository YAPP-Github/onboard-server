package com.yapp.bol.terms

import com.yapp.bol.auth.UserId
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component

@Component
internal class TermsCommandRepositoryImpl(
    private val agreedTermsRepository: AgreedTermsRepository,
) : TermsCommandRepository {

    @Transactional
    override fun agreeTerms(userId: UserId, termsCode: List<TermsCode>) {
        val list = agreedTermsRepository.findByUserId(userId.value)

        val entities = termsCode
            .filter { code -> list.any { entity -> code == entity.code } }
            .map { AgreedTermsEntity.of(userId.value, it) }

        agreedTermsRepository.saveAll(entities)
    }
}
