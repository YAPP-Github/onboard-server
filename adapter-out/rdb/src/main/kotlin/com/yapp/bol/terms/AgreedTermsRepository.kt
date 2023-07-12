package com.yapp.bol.terms

import org.springframework.data.jpa.repository.JpaRepository

internal interface AgreedTermsRepository : JpaRepository<AgreedTermsEntity, Long> {

    fun findByUserId(userId: Long): List<AgreedTermsEntity>
    fun findByUserIdAndCode(userId: Long, code: TermsCode): AgreedTermsEntity?
}
