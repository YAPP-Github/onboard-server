package com.yapp.bol.onboarding

import com.yapp.bol.auth.UserId

interface OnboardingService {
    fun getRemainOnboarding(userId: UserId): List<OnboardingType>
}
