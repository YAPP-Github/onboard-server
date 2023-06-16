package com.yapp.bol.auth

import com.yapp.bol.AuditingEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import jakarta.persistence.Table

@Table(name = "auth_social")
@Entity
@IdClass(SocialInfo::class)
internal class AuthSocialEntity(
    socialType: SocialType,
    socialId: String,
    userId: UserId,
) : AuditingEntity() {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "social_type")
    var socialType: SocialType = socialType
        protected set

    @Id
    @Column(name = "social_id")
    var socialId: String = socialId
        protected set

    @Column(name = "users_id")
    var userId: Long = userId.value
        protected set
}
