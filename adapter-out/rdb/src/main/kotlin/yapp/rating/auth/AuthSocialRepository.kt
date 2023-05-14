package yapp.rating.auth

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal interface AuthSocialRepository : JpaRepository<AuthSocialEntity, SocialInfo>
