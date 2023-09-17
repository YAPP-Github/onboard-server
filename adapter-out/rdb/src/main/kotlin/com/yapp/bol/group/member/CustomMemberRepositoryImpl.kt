package com.yapp.bol.group.member

import com.querydsl.jpa.impl.JPAQueryFactory
import com.yapp.bol.group.member.dto.PaginationCursorMemberRequest

class CustomMemberRepositoryImpl(
    val queryFactory: JPAQueryFactory
) : CustomMemberRepository {
    override fun getByGroupIdWithCursor(request: PaginationCursorMemberRequest): List<MemberEntity> {
        val groupId = request.groupId.value
        val nickname = request.nickname
        val role = request.role
        val size = request.size
        val cursor = request.cursor

        val member = QMemberEntity.memberEntity

        var express = member.groupId.eq(groupId).and(member.deleted.isFalse)

        if (nickname != null) {
            express = express.and(member.nickname.like("%$nickname%"))
        }

        if (role != null) {
            express = express.and(member.role.eq(role))
        }

        if (cursor != null) {
            express = express.and(member.nickname.gt(cursor))
        }

        return queryFactory.select(member)
            .from(member)
            .where(express)
            .orderBy(member.nickname.asc())
            .limit(size.toLong())
            .fetch()
    }
}
