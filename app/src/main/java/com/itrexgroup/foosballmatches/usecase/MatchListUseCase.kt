package com.itrexgroup.foosballmatches.usecase

import com.itrexgroup.domain.dto.MatchDto
import com.itrexgroup.foosballmatches.repository.DatabaseRepository
import javax.inject.Inject

class MatchListUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {

    val matchList = databaseRepository.getMatchList()

    fun updateMatch(matchDto: MatchDto) {
        databaseRepository.updateMatch(matchDto)
    }

    fun deleteMatch(id: String) {
        databaseRepository.deleteMatch(id)
    }
}