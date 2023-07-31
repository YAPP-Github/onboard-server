package com.yapp.bol.season

import com.yapp.bol.AuditingEntity
import com.yapp.bol.group.GroupId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "season")
class SeasonEntity : AuditingEntity() {
    @Id
    @Column(name = "season_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
        protected set

    @Column(name = "group_id")
    var groupId: Long = 0
        protected set

    companion object {
        fun of(id: Long, groupId: Long): SeasonEntity {
            return SeasonEntity().apply {
                this.id = id
                this.groupId = groupId
            }
        }
    }
}

internal fun Season.toEntity(): SeasonEntity = SeasonEntity.of(
    id = this.id.value,
    groupId = this.groupId.value,
)

internal fun SeasonEntity.toDomain(): Season = Season(
    id = SeasonId(id),
    groupId = GroupId(groupId),
)
