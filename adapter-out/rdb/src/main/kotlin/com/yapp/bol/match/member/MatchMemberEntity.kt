package com.yapp.bol.match.member

import com.yapp.bol.AuditingEntity
import com.yapp.bol.group.member.MemberId
import com.yapp.bol.match.MatchEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "match_member")
class MatchMemberEntity : AuditingEntity() {
    @Id
    @Column(name = "match_member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
        protected set

    @Column(name = "member_id")
    var memberId: Long = 0
        protected set

    @Column(name = "score")
    var score: Int = 0
        protected set

    @Column(name = "ranking")
    var ranking: Int = 0
        protected set

    @Column(name = "previous_score")
    var previousScore: Int = 0
        protected set

    @ManyToOne
    @JoinColumn(name = "match_id")
    lateinit var match: MatchEntity
        protected set

    companion object {
        fun of(
            id: Long,
            memberId: Long,
            score: Int,
            ranking: Int,
            match: MatchEntity
        ) = MatchMemberEntity().apply {
            this.id = id
            this.memberId = memberId
            this.score = score
            this.ranking = ranking
            this.match = match
        }
    }
}

internal fun MatchMember.toEntity(match: MatchEntity): MatchMemberEntity = MatchMemberEntity.of(
    id = this.id.value,
    memberId = this.memberId.value,
    score = this.score,
    ranking = this.ranking,
    match = match,
)

internal fun MatchMemberEntity.toDomain(): MatchMember = MatchMember(
    id = MatchMemberId(id),
    memberId = MemberId(memberId),
    score = score,
    ranking = ranking,
)
