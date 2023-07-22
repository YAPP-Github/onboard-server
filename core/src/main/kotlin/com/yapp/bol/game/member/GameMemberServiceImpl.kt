package com.yapp.bol.game.member

import com.yapp.bol.game.GameId
import com.yapp.bol.group.GroupId
import com.yapp.bol.group.member.MemberId
import com.yapp.bol.match.dto.CreateMatchDto
import com.yapp.bol.match.dto.CreateMatchMemberDto
import com.yapp.bol.season.SeasonService
import org.springframework.stereotype.Service

@Service
class GameMemberServiceImpl(
    private val gameMemberQueryRepository: GameMemberQueryRepository,
    private val gameMemberCommandRepository: GameMemberCommandRepository,
    private val seasonService: SeasonService
) : GameMemberService {
    private fun getOrCreateGameMembers(gameId: GameId, groupId: GroupId, memberIds: List<MemberId>): List<GameMember> {
        return memberIds.map { memberId ->
            getOrCreateGameMember(
                memberId = memberId,
                gameId = gameId,
                groupId = groupId
            )
        }
    }

    private fun getOrCreateGameMember(memberId: MemberId, gameId: GameId, groupId: GroupId): GameMember {
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

    private fun processMatch(gameMembers: List<GameMember>, matchMemberDtos: List<CreateMatchMemberDto>): List<GameMember> {
        val gameMembersMap = gameMembers.associateBy { it.memberId }

        return matchMemberDtos.map { matchMemberDto ->
            gameMembersMap[matchMemberDto.memberId]!!.processMatch(additionalScore = matchMemberDto.score)
        }
    }

    override fun processScore(createMatchDto: CreateMatchDto): List<GameMember> {
        val gameMembers = getOrCreateGameMembers(
            gameId = createMatchDto.gameId,
            groupId = createMatchDto.groupId,
            memberIds = createMatchDto.createMatchMemberDtos.map { it.memberId }
        )

        return processMatch(
            gameMembers = gameMembers,
            matchMemberDtos = createMatchDto.createMatchMemberDtos
        )
    }
}
