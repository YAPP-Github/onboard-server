package com.yapp.bol.terms

import org.springframework.data.jpa.repository.JpaRepository

internal interface AgreedTermsRepository : JpaRepository<AgreedTermsEntity, Long> {
    fun findByUserIdAndCategory(userId: Long, category: TermsCategory): AgreedTermsEntity?
}
