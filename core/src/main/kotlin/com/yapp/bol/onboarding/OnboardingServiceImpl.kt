package com.yapp.bol.onboarding

import com.yapp.bol.NotFoundUserException
import com.yapp.bol.auth.UserId
import com.yapp.bol.user.UserQueryRepository
import org.springframework.stereotype.Service

@Service
class OnboardingServiceImpl(
    private val userQueryRepository: UserQueryRepository,
) : OnboardingService {
    override fun getRemainOnboarding(userId: UserId): List<OnboardingType> {
        val user = userQueryRepository.getUser(userId) ?: throw NotFoundUserException

        return if (user.nickname == null) emptyList()
        else listOf(OnboardingType.TERMS, OnboardingType.NICKNAME)
    }
}
