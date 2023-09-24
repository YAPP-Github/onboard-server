package com.yapp.bol.game.rating.strategy

import com.yapp.bol.game.rating.dto.RatingInput

interface RatingStrategy {
    fun compute(input: RatingInput): Int
}
