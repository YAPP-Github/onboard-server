package com.yapp.bol.match

import com.yapp.bol.InvalidMatchMemberException
import com.yapp.bol.game.GameId
import com.yapp.bol.game.member.GameMember
import com.yapp.bol.game.member.GameMemberService
import com.yapp.bol.group.GroupId
import com.yapp.bol.match.dto.CreateMatchDto
import com.yapp.bol.match.dto.CreateMatchMemberDto
import com.yapp.bol.match.dto.MatchWithMatchMemberList
import com.yapp.bol.match.dto.toDomain
import com.yapp.bol.match.member.MatchMember
import com.yapp.bol.season.SeasonService
import org.springframework.stereotype.Service

@Service
class MatchServiceImpl(
    private val matchCommandRepository: MatchCommandRepository,
    private val gameMemberService: GameMemberService,
    private val seasonService: SeasonService
) : MatchService {
    override fun createMatch(createMatchDto: CreateMatchDto): MatchWithMatchMemberList {
        val gameMembers = getGameMembers(
            gameId = createMatchDto.gameId,
            groupId = createMatchDto.groupId,
            matchMemberDtos = createMatchDto.matchMembers
        )

        val matchMembers = convertMatchMembers(
            gameMembers = gameMembers,
            matchMemberDtos = createMatchDto.matchMembers
        )

        processMatch(
            gameMembers = gameMembers,
            matchMemberDtos = createMatchDto.matchMembers
        )

        return transactionMatch(
            createMatchDto = createMatchDto,
            matchMembers = matchMembers,
            gameMembers = gameMembers
        )
    }

    private fun getGameMembers(gameId: GameId, groupId: GroupId, matchMemberDtos: List<CreateMatchMemberDto>): List<GameMember> {
        return matchMemberDtos.map { matchMemberDto ->
            gameMemberService.getOrCreateGameMember(
                memberId = matchMemberDto.memberId,
                gameId = gameId,
                groupId = groupId
            )
        }
    }

    private fun transactionMatch(createMatchDto: CreateMatchDto, matchMembers: List<MatchMember>, gameMembers: List<GameMember>): MatchWithMatchMemberList {
        val season = seasonService.getOrCreateSeason(groupId = createMatchDto.groupId)

        val matchWithMatchMembers = createMatchDto.toDomain(
            matchMembers = matchMembers,
            season = season
        )

        return matchCommandRepository.createMatch(matchWithMatchMembers = matchWithMatchMembers, gameMembers = gameMembers)
    }

    private fun processMatch(gameMembers: List<GameMember>, matchMemberDtos: List<CreateMatchMemberDto>) {
        val gameMembersMap = gameMembers.associateBy { it.memberId }

        matchMemberDtos.forEach { matchMemberDto ->
            gameMembersMap[matchMemberDto.memberId]?.processMatch(matchScore = matchMemberDto.score)
        }
    }

    private fun convertMatchMembers(gameMembers: List<GameMember>, matchMemberDtos: List<CreateMatchMemberDto>): List<MatchMember> {
        val finalScoresMap = gameMembers.associateBy({ it.memberId }, { it.finalScore })

        val matchMembers = matchMemberDtos.map { matchMemberDto ->
            val finalScore = finalScoresMap[matchMemberDto.memberId] ?: throw InvalidMatchMemberException

            matchMemberDto.toDomain(finalScore)
        }

        return matchMembers
    }
}
