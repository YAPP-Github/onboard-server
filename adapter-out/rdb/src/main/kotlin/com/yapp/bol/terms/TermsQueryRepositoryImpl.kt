package com.yapp.bol.terms

import com.yapp.bol.auth.UserId
import org.springframework.stereotype.Component

@Component
internal class TermsQueryRepositoryImpl(
    private val agreedTermsRepository: AgreedTermsRepository,
) : TermsQueryRepository {

    override fun getAgreedTermsByUserId(userId: UserId): List<TermsCode> {
        return agreedTermsRepository.findByUserId(userId.value).map { it.code }
    }
}
