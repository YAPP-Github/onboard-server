package com.yapp.bol.match

import com.yapp.bol.game.member.GameMember
import com.yapp.bol.game.member.GameMemberRepository
import com.yapp.bol.game.member.toEntity
import com.yapp.bol.match.dto.MatchWithMatchMemberList
import com.yapp.bol.match.member.MatchMemberEntity
import com.yapp.bol.match.member.MatchMemberRepository
import com.yapp.bol.match.member.toDomain
import com.yapp.bol.match.member.toEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
internal class MatchCommandRepositoryImpl(
    private val matchRepository: MatchRepository,
    private val matchMemberRepository: MatchMemberRepository,
    private val gameMemberRepository: GameMemberRepository
) : MatchCommandRepository {
    @Transactional
    override fun createMatch(matchWithMatchMembers: MatchWithMatchMemberList, gameMembers: List<GameMember>): MatchWithMatchMemberList {
        val match = matchRepository.save(
            matchWithMatchMembers.match.toEntity()
        ).toDomain()

        // TODO: 연관 관계 + CASCADE 설정
        val matchMemberEntities: List<MatchMemberEntity> = matchWithMatchMembers.matchMembers
            .map {
                it.toEntity(
                    matchId = it.matchId,
                    memberId = it.memberId
                )
            }

        val matchMembers = matchMemberRepository.saveAll(matchMemberEntities)
            .map {
                it.toDomain()
            }

        gameMemberRepository.saveAll(
            gameMembers.map {
                it.toEntity()
            }
        )

        return MatchWithMatchMemberList(
            match = match,
            matchMembers = matchMembers
        )
    }
}
