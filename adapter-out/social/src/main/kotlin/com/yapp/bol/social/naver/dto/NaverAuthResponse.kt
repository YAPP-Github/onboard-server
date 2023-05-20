package com.yapp.bol.social.naver.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.yapp.bol.auth.SocialUser

internal data class NaverUserResponse(
    val resultCode: String,
    val message: String,
    @JsonProperty("response")
    val userInfo: NaverUserInfoResponse,
)

internal class NaverUserInfoResponse(
    override val id: String,
//    네이버 콘솔에서 추가 필요
//    val nickname: String,
//    val email: String,
) : SocialUser
