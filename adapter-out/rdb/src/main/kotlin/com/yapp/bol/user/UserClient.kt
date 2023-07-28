package com.yapp.bol.user

import com.yapp.bol.NotFoundUserException
import com.yapp.bol.auth.UserId
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
internal class UserClient(
    private val userRepository: UserRepository,
) : UserQueryRepository, UserCommandRepository {
    override fun getUser(userId: UserId): User? {
        val userEntity = userRepository.findByIdOrNull(userId.value) ?: throw NotFoundUserException

        return userEntity.toDomain()
    }

    @Transactional
    override fun updateUser(user: User) {
        val entity = userRepository.findByIdOrNull(user.id.value) ?: throw NotFoundUserException

        entity.name = user.nickname

        userRepository.save(entity)
    }

    override fun delete(userId: UserId) {
        val entity = userRepository.findByIdOrNull(userId.value) ?: return

        entity.delete()

        userRepository.save(entity)
    }
}

private fun UserEntity.toDomain(): User = User(
    id = UserId(this.id),
    nickname = this.name
)
