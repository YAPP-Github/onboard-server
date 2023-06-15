package com.yapp.bol.game.dto

import com.yapp.bol.file.FileNameConverter
import com.yapp.bol.game.Game

data class GameResponse(
    val id: Long,
    val name: String,
    val minMember: Int,
    val maxMember: Int,
    val img: String,
)

fun Game.toResponse(): GameResponse = GameResponse(
    id = this.id.value,
    name = this.name,
    minMember = this.minMember,
    maxMember = this.maxMember,
    img = FileNameConverter.convertFileUrl(this.img),
)
