package com.yapp.bol.group.member

interface MemberCommandRepository {
    fun createMember(member: Member): Member

    fun createMembers(members: MemberList): MemberList
}
