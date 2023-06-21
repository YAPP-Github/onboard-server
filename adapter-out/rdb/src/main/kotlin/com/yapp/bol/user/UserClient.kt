package com.yapp.bol.user

import com.yapp.bol.NotFoundUserException
import com.yapp.bol.auth.UserId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
internal class UserClient(
    private val userRepository: UserRepository,
) : UserQueryRepository {
    override fun getUser(userId: UserId): User? {
        val userEntity = userRepository.findByIdOrNull(userId.value) ?: throw NotFoundUserException

        return userEntity.toDomain()
    }
}

private fun UserEntity.toDomain(): User = User(
    id = UserId(this.id),
    nickname = this.name
)
