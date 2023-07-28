package com.yapp.bol.user

import com.yapp.bol.InvalidNicknameException
import com.yapp.bol.auth.UserId
import com.yapp.bol.group.member.MemberService
import com.yapp.bol.validate.NicknameValidator
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userQueryRepository: UserQueryRepository,
    private val userCommandRepository: UserCommandRepository,
    private val memberService: MemberService,
) : UserService {

    override fun getUser(userId: UserId): User? {
        return userQueryRepository.getUser(userId)
    }

    override fun putUser(user: User) {
        NicknameValidator.validate(user.nickname ?: throw InvalidNicknameException)

        userCommandRepository.updateUser(user)
    }

    override fun unregister(userId: UserId) {

        memberService.unregister(userId)
        userCommandRepository.delete(userId)
    }
}
