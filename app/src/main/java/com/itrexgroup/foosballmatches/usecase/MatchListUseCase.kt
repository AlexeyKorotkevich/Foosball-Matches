package com.itrexgroup.foosballmatches.usecase

import com.itrexgroup.data.repository.DatabaseRepositoryImpl
import com.itrexgroup.domain.dto.MatchDto
import io.reactivex.Completable
import javax.inject.Inject

class MatchListUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepositoryImpl
) {

    val matchList = databaseRepository.getMatchList()

    fun updateMatch(matchDto: MatchDto): Completable = databaseRepository.updateMatch(matchDto)

    fun deleteMatch(id: String): Completable = databaseRepository.deleteMatch(id)
}