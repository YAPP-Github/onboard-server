package com.yapp.bol.group

import com.yapp.bol.game.GameId
import com.yapp.bol.pagination.offset.PaginationOffsetResponse

interface GroupQueryRepository {
    fun findById(id: GroupId): Group?

    fun findByNameLike(name: String?, pageNumber: Int, pageSize: Int): PaginationOffsetResponse<Group>

    fun getLeaderBoardList(groupId: GroupId, gameId: GameId): List<LeaderBoardMember>
}
