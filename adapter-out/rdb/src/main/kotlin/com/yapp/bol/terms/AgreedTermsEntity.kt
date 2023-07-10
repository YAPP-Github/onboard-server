package com.yapp.bol.terms

import com.yapp.bol.AuditingEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "agreed_terms")
internal class AgreedTermsEntity : AuditingEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agreed_terms_id")
    var id: Long = 0
        protected set

    @Column(name = "user_id")
    var userId: Long = 0
        protected set

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    lateinit var category: TermsCategory
        protected set

    @Column(name = "version")
    var version: Int = 0
        protected set

    fun updateVersion(version: Int) {
        if (this.version >= version) return
        this.version = version
    }

    companion object {
        fun of(
            userId: Long,
            category: TermsCategory,
            version: Int,
        ): AgreedTermsEntity = AgreedTermsEntity().apply {
            this.userId = userId
            this.category = category
            this.version = version
        }
    }
}
