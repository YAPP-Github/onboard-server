package com.yapp.bol.match

import com.yapp.bol.game.member.GameMember
import com.yapp.bol.game.member.GameMemberService
import com.yapp.bol.match.dto.CreateMatchDto
import com.yapp.bol.match.dto.CreateMatchMemberDto
import com.yapp.bol.match.dto.toDomain
import com.yapp.bol.season.SeasonService
import org.springframework.stereotype.Service

@Service
class MatchServiceImpl(
    private val matchCommandRepository: MatchCommandRepository,
    private val gameMemberService: GameMemberService,
    private val seasonService: SeasonService
) : MatchService {
    // TODO: @Transactional
    override fun createMatch(createMatchDto: CreateMatchDto): Match {
        // TODO: 검증 로직 추가, member ID 랑 groupId
        // TODO: game id 검증

        val gameMembers = gameMemberService.getOrCraeteGameMembers(
            gameId = createMatchDto.gameId,
            groupId = createMatchDto.groupId,
            memberIds = createMatchDto.createMatchMemberDtos.map { it.memberId }
        )

        val season = seasonService.getOrCreateSeason(groupId = createMatchDto.groupId)

        val match = createMatchDto.toDomain(season)

        processMatch(
            gameMembers = gameMembers,
            matchMemberDtos = createMatchDto.createMatchMemberDtos
        )

        return matchCommandRepository.createMatch(match = match, gameMembers = gameMembers)
    }

    private fun processMatch(gameMembers: List<GameMember>, matchMemberDtos: List<CreateMatchMemberDto>) {
        val gameMembersMap = gameMembers.associateBy { it.memberId }

        matchMemberDtos.forEach { matchMemberDto ->
            gameMembersMap[matchMemberDto.memberId]?.processMatch(additionalScore = matchMemberDto.score)
        }
    }
}
