package com.yapp.bol.group.member

import com.querydsl.jpa.impl.JPAQueryFactory
import com.yapp.bol.pagination.cursor.PaginationCursorRequest

class CustomMemberRepositoryImpl(
    val queryFactory: JPAQueryFactory
) : CustomMemberRepository {
    override fun getByGroupIdWithCursor(
        groupId: Long,
        nickname: String?,
        cursor: PaginationCursorRequest<String>
    ): List<MemberEntity> {
        val member = QMemberEntity.memberEntity

        var express = member.groupId.eq(groupId)

        if (nickname != null) {
            express = express.and(member.nickname.like("%$nickname%"))
        }

        if (cursor.cursor != null) {
            express = express.and(member.nickname.gt(cursor.cursor))
        }

        return queryFactory.select(member)
            .from(member)
            .where(express)
            .orderBy(member.nickname.asc())
            .limit(cursor.size.toLong())
            .fetch()
    }
}
