package com.yapp.bol.game.member

import com.yapp.bol.game.GameId
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.MemberId
import org.springframework.stereotype.Repository

@Repository
class GameMemberQueryRepositoryImpl(
    private val gameMemberRepository: GameMemberRepository
) : GameMemberQueryRepository {
    override fun findGameMember(memberId: MemberId, gameId: GameId, groupId: GroupId): GameMember? {
        return gameMemberRepository.findByMemberIdAndGameId(memberId = memberId.value, gameId = gameId.value)?.toDomain()
    }
}
