package com.yapp.bol.auth

import org.springframework.data.jpa.repository.JpaRepository

internal interface AuthSocialRepository : JpaRepository<AuthSocialEntity, SocialInfo> {
    fun findByUserId(userId: UserId): AuthSocialEntity?
}
