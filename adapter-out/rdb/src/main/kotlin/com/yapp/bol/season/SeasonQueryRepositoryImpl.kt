package com.yapp.bol.season

import com.yapp.bol.group.GroupId
import org.springframework.stereotype.Repository

@Repository
internal class SeasonQueryRepositoryImpl(
    private val seasonRepository: SeasonRepository
) : SeasonQueryRepository {
    override fun getSeason(groupId: GroupId): Season? {
        return seasonRepository.findByGroupId(groupId.value)?.toDomain()
    }
}
