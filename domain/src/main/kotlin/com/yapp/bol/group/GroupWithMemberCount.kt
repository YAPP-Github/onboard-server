package com.yapp.bol.group

class GroupWithMemberCount(
    id: Long,
    name: String,
    description: String,
    organization: String,
    profileImageUrl: String,
    val memberCount: Int
) : BaseGroup(
    id = id,
    name = name,
    description = description,
    organization = organization,
    profileImageUrl = profileImageUrl
)

fun Group.toGroupWithMemberCount(memberCount: Int): GroupWithMemberCount =
    GroupWithMemberCount(
        id = id,
        name = name,
        description = description,
        organization = organization,
        profileImageUrl = profileImageUrl,
        memberCount = memberCount
    )
