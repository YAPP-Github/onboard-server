package com.yapp.bol.season

import org.springframework.stereotype.Repository

@Repository
internal class SeasonCommandRepositoryImpl(
    private val seasonRepository: SeasonRepository
) : SeasonCommandRepository {
    override fun createSeason(season: Season): Season {
        return seasonRepository.save(season.toEntity()).toDomain()
    }
}
