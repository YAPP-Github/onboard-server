package com.yapp.bol.auth.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.yapp.bol.onboarding.OnboardingType

@JsonInclude(JsonInclude.Include.NON_NULL)
data class LoginResponse(
    val accessToken: String,
    val refreshToken: String?,
    val onboarding: List<OnboardingType>,
)
