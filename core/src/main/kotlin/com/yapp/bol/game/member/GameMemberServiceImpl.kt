package com.yapp.bol.game.member

import com.yapp.bol.InvalidMatchMemberException
import com.yapp.bol.game.GameService
import com.yapp.bol.game.rating.dto.MatchOutcome
import com.yapp.bol.game.rating.dto.RatingInput
import com.yapp.bol.game.rating.strategy.EloRatingStrategy
import com.yapp.bol.group.member.MemberId
import com.yapp.bol.match.dto.CreateMatchDto
import com.yapp.bol.match.dto.CreateMatchMemberDto
import com.yapp.bol.season.SeasonService
import org.springframework.stereotype.Service

@Service
class GameMemberServiceImpl(
    private val gameMemberQueryRepository: GameMemberQueryRepository,
    private val gameMemberCommandRepository: GameMemberCommandRepository,
    private val gameService: GameService,
    private val seasonService: SeasonService,
) : GameMemberService {

    override fun processScore(createMatchDto: CreateMatchDto): List<GameMember> {
        validateMatch(createMatchDto = createMatchDto)

        val matchMemberInfos = createMatchDto.createMatchMemberDtos

        val gameMembers = matchMemberInfos.map {
            getOrCreateGameMember(
                createMatchMemberDto = it,
                createMatchDto = createMatchDto
            )
        }

        val inputMap = createMatchInputMap(gameMembers = gameMembers, createMatchDto = createMatchDto)

        return gameMembers.map {
            val input = inputMap[it.memberId] ?: throw InvalidMatchMemberException

            it.updateScore(input = input, strategy = EloRatingStrategy)
        }
    }

    private fun validateMatch(createMatchDto: CreateMatchDto) {
        val gameId = createMatchDto.gameId
        val memberCount = createMatchDto.createMatchMemberDtos.size

        if (!gameService.validateMemberSize(gameId = gameId, memberCount = memberCount)) {
            throw InvalidMatchMemberException
        }
    }

    private fun createMatchInputMap(
        gameMembers: List<GameMember>,
        createMatchDto: CreateMatchDto
    ): Map<MemberId, RatingInput> {
        val memberMatchInfoMap = createMatchDto.createMatchMemberDtos.associateBy { it.memberId }

        val matchOutcomes = gameMembers.map {
            val memberMatchInfo = memberMatchInfoMap[it.memberId] ?: throw InvalidMatchMemberException

            MatchOutcome(
                memberId = it.memberId,
                score = memberMatchInfo.score,
                ranking = memberMatchInfo.ranking,
                finalScore = it.finalScore
            )
        }

        return matchOutcomes.associate { me ->
            me.memberId to RatingInput(
                me = me,
                opponents = matchOutcomes.filter { me.memberId != it.memberId }
            )
        }
    }

    private fun getOrCreateGameMember(
        createMatchMemberDto: CreateMatchMemberDto,
        createMatchDto: CreateMatchDto
    ): GameMember {
        val memberId = createMatchMemberDto.memberId
        val gameId = createMatchDto.gameId
        val groupId = createMatchDto.groupId

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
