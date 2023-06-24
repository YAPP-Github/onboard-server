package com.yapp.bol.user

import com.yapp.bol.auth.UserId
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userQueryRepository: UserQueryRepository,
) : UserService {
    override fun getUser(userId: UserId): User? {
        return userQueryRepository.getUser(userId)
    }
}
