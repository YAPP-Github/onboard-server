package com.yapp.bol.game.member

import com.yapp.bol.group.GroupId
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class GameMemberCommandRepositoryImpl(
    private val gameMemberRepository: GameMemberRepository
) : GameMemberCommandRepository {
    @Transactional
    override fun createGameMember(gameMember: GameMember, groupId: GroupId): GameMember {
        return gameMemberRepository.save(gameMember.toEntity())
            .toDomain()
    }
}
