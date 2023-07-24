package com.yapp.bol.game.member

import com.yapp.bol.game.GameId
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.MemberId
import com.yapp.bol.season.SeasonService
import org.springframework.stereotype.Service

@Service
class GameMemberServiceImpl(
    private val gameMemberQueryRepository: GameMemberQueryRepository,
    private val gameMemberCommandRepository: GameMemberCommandRepository,
    private val seasonService: SeasonService
) : GameMemberService {
    override fun getOrCreateGameMember(memberId: MemberId, gameId: GameId, groupId: GroupId): GameMember {
        val gameMember = gameMemberQueryRepository.findGameMember(memberId = memberId, gameId = gameId, groupId = groupId)

        if (gameMember != null) {
            return gameMember
        }

        val season = seasonService.getOrCreateSeason(groupId = groupId)

        return gameMemberCommandRepository.createGameMember(
            GameMember.of(
                gameId = gameId,
                memberId = memberId,
                season = season
            ),
            groupId = groupId
        )
    }
}
