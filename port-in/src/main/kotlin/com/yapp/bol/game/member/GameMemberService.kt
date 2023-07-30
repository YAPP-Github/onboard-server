package com.yapp.bol.game.member

import com.yapp.bol.match.dto.CreateMatchDto

interface GameMemberService {
    fun processScore(createMatchDto: CreateMatchDto): List<GameMember>
}
