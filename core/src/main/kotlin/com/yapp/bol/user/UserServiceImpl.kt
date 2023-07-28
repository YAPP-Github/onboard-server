package com.yapp.bol.user

import com.yapp.bol.InvalidNicknameException
import com.yapp.bol.auth.UserId
import com.yapp.bol.validate.NicknameValidator
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userQueryRepository: UserQueryRepository,
    private val userCommandRepository: UserCommandRepository,
) : UserService {

    override fun getUser(userId: UserId): User? {
        return userQueryRepository.getUser(userId)
    }

    override fun putUser(user: User) {
        NicknameValidator.validate(user.nickname ?: throw InvalidNicknameException)

        userCommandRepository.updateUser(user)
    }

    override fun delete(userId: UserId) {
        userCommandRepository.delete(userId)
    }
}
