package yapp.rating.auth

import java.io.Serializable

data class SocialInfo(
    val socialType: SocialType,
    val socialId: String,
) : Serializable
