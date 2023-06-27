package com.yapp.bol.match

import com.yapp.bol.AuditingEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "match_member")
class MatchMemberEntity : AuditingEntity() {
    @Id
    @Column(name = "match_member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
        protected set

    @Column(name = "match_id")
    var matchId: Long = 0
        protected set

    @Column(name = "member_id")
    var memberId: Long = 0
        protected set

    @Column(name = "score")
    var score: Int = 0
        protected set

    @Column(name = "rank")
    var rank: Int = 0
        protected set

    @Column(name = "previous_score")
    var previousScore: Int = 0
        protected set

    @Column(name = "order")
    var order: Int = 0
        protected set
}
