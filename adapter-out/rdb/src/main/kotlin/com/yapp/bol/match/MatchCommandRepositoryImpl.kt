package com.yapp.bol.match

import com.yapp.bol.game.member.GameMember
import com.yapp.bol.game.member.GameMemberRepository
import com.yapp.bol.game.member.toEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
internal class MatchCommandRepositoryImpl(
    private val matchRepository: MatchRepository,
    private val gameMemberRepository: GameMemberRepository
) : MatchCommandRepository {
    @Transactional
    override fun createMatch(match: Match, gameMembers: List<GameMember>): Match {
        val match = matchRepository.save(match.toEntity()).toDomain()

        gameMemberRepository.saveAll(
            gameMembers.map {
                it.toEntity()
            }
        )

        return match
    }
}
