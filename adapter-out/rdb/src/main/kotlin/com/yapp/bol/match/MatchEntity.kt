package com.yapp.bol.match

import com.yapp.bol.AuditingEntity
import com.yapp.bol.game.GameId
import com.yapp.bol.group.GroupId
import com.yapp.bol.match.member.MatchMember
import com.yapp.bol.match.member.MatchMemberEntity
import com.yapp.bol.match.member.toDomain
import com.yapp.bol.match.member.toEntity
import com.yapp.bol.season.SeasonEntity
import com.yapp.bol.season.toDomain
import com.yapp.bol.season.toEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "match_table")
class MatchEntity : AuditingEntity() {
    @Id
    @Column(name = "match_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
        protected set

    @Column(name = "memo")
    lateinit var memo: String
        protected set

    @Column(name = "match_image_url")
    lateinit var matchImageUrl: String
        protected set

    @Column(name = "matched_date")
    lateinit var matchedDate: LocalDateTime
        protected set

    @Column(name = "member_count")
    var memberCount: Int = 0
        protected set

    @OneToMany(mappedBy = "match", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    lateinit var matchMembers: List<MatchMemberEntity>
        protected set

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    lateinit var season: SeasonEntity
        protected set

    @Column(name = "game_id")
    var gameId: Long = 0
        protected set

    @Column(name = "group_id")
    var groupId: Long = 0
        protected set

    companion object {
        fun of(
            id: Long,
            gameId: Long,
            groupId: Long,
            matchedDate: LocalDateTime,
            memberCount: Int,
            season: SeasonEntity,
            matchMembers: List<MatchMember>
        ) = MatchEntity().apply {
            this.id = id
            this.gameId = gameId
            this.groupId = groupId
            this.matchedDate = matchedDate
            this.memberCount = memberCount
            this.season = season
            this.matchMembers = matchMembers.map { it.toEntity(this) }
        }
    }
}

internal fun Match.toEntity(): MatchEntity = MatchEntity.of(
    id = this.id.value,
    gameId = this.gameId.value,
    groupId = this.groupId.value,
    matchedDate = this.matchedDate,
    memberCount = this.memberCount,
    season = this.season.toEntity(),
    matchMembers = this.matchMembers
)

internal fun MatchEntity.toDomain(): Match = Match(
    id = MatchId(this.id),
    gameId = GameId(this.gameId),
    groupId = GroupId(this.groupId),
    matchedDate = this.matchedDate,
    memberCount = this.memberCount,
    season = this.season.toDomain(),
    matchMembers = this.matchMembers.map { it.toDomain() }
)
