package com.yapp.bol.group

import com.yapp.bol.AuditingEntity
import com.yapp.bol.group.member.MemberEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "group_table")
internal class GroupEntity(
    id: Long = 0,
    name: String,
    description: String,
    organization: String,
    profileImageUrl: String,
    accessCode: String,
    members: List<MemberEntity>,
) : AuditingEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false)
    val id: Long = id

    @Column(name = "name")
    val name: String = name

    @Column(name = "description")
    val description: String = description

    @Column(name = "organization")
    val organization: String = organization

    @Column(name = "profileImageUrl")
    val profileImageUrl: String = profileImageUrl

    @OneToMany(mappedBy = "groupId", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val members: List<MemberEntity> = members

    @Column(name = "access_code")
    val accessCode: String = accessCode

    @Column(name = "deleted")
    val deleted: Boolean = false
}
