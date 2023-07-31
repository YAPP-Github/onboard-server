package com.yapp.bol.game.member

import com.yapp.bol.AuditingEntity
import com.yapp.bol.game.GameId
import com.yapp.bol.group.member.MemberId
import com.yapp.bol.season.SeasonEntity
import com.yapp.bol.season.toDomain
import com.yapp.bol.season.toEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "game_member")
class GameMemberEntity : AuditingEntity() {
    // TODO: 혼합키로 만들기
    @Id
    @Column(name = "game_member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
        protected set

    @Column(name = "game_id")
    var gameId: Long = 0
        protected set

    @Column(name = "member_id")
    var memberId: Long = 0
        protected set

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    lateinit var season: SeasonEntity
        protected set

    @Column(name = "final_score")
    var finalScore: Int = 0
        protected set

    @Column(name = "match_count")
    var matchCount: Int = 0
        protected set

    @Column(name = "winning_percentage")
    var winningPercentage: Double = 0.0
        protected set

    companion object {
        fun of(
            id: Long,
            gameId: Long,
            memberId: Long,
            season: SeasonEntity,
            finalScore: Int,
            matchCount: Int,
            winningPercentage: Double
        ): GameMemberEntity {
            return GameMemberEntity().apply {
                this.id = id
                this.gameId = gameId
                this.memberId = memberId
                this.season = season
                this.finalScore = finalScore
                this.matchCount = matchCount
                this.winningPercentage = winningPercentage
            }
        }
    }
}

internal fun GameMemberEntity.toDomain(): GameMember = GameMember(
    id = GameMemberId(id),
    gameId = GameId(gameId),
    memberId = MemberId(memberId),
    season = season.toDomain(),
    finalScore = finalScore,
    matchCount = matchCount,
    winningPercentage = winningPercentage,
)

internal fun GameMember.toEntity(): GameMemberEntity = GameMemberEntity.of(
    id = id.value,
    gameId = gameId.value,
    memberId = memberId.value,
    season = season.toEntity(),
    finalScore = finalScore,
    matchCount = matchCount,
    winningPercentage = winningPercentage
)
