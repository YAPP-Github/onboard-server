package com.yapp.bol.match

import com.yapp.bol.game.member.GameMemberService
import com.yapp.bol.match.dto.CreateMatchDto
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
        val gameMembers = gameMemberService.processScore(createMatchDto = createMatchDto)

        val season = seasonService.getOrCreateSeason(groupId = createMatchDto.groupId)

        val match = createMatchDto.toDomain(season = season)

        return matchCommandRepository.createMatch(match = match, gameMembers = gameMembers)
    }
}
