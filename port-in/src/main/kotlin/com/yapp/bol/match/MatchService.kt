package com.yapp.bol.match

import com.yapp.bol.match.dto.CreateMatchDto
import com.yapp.bol.match.dto.MatchWithMatchMemberList

interface MatchService {
    fun createMatch(createMatchDto: CreateMatchDto): MatchWithMatchMemberList
}
