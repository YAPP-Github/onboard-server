package com.yapp.bol.social.kakao.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.yapp.bol.auth.SocialUser

@JsonIgnoreProperties(ignoreUnknown = true)
internal data class KakaoUserResponse(
    override val id: String,
    @JsonProperty("kakao_account") val kakaoAccount: KakaoAccount,
) : SocialUser

internal class KakaoAccount
