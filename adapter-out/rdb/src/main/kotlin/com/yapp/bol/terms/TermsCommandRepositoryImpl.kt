package com.yapp.bol.terms

import com.yapp.bol.auth.UserId
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component

@Component
internal class TermsCommandRepositoryImpl(
    private val agreedTermsRepository: AgreedTermsRepository,
) : TermsCommandRepository {

    @Transactional
    override fun agreeTerms(userId: UserId, termsCode: TermsCode) {
        val entity = agreedTermsRepository.findByUserIdAndCategory(userId.value, termsCode.category)
            ?: termsCode.toEntity(userId)

        entity.updateVersion(termsCode.version)

        agreedTermsRepository.save(entity)
    }

    private fun TermsCode.toEntity(userId: UserId): AgreedTermsEntity = AgreedTermsEntity.of(
        userId = userId.value,
        category = this.category,
        version = this.version,
    )
}
