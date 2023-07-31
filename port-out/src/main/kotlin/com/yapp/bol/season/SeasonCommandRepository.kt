package com.yapp.bol.season

interface SeasonCommandRepository {
    fun createSeason(season: Season): Season
}
