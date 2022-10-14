package com.itrexgroup.domain.usecase

import com.itrexgroup.domain.dto.MatchDto
import com.itrexgroup.domain.repository.DatabaseRepository
import io.reactivex.Completable

class MatchListUseCase(
    private val databaseRepository: DatabaseRepository
) {

    val matchList = databaseRepository.getMatchList()

    fun updateMatch(matchDto: MatchDto): Completable = databaseRepository.updateMatch(matchDto)

    fun deleteMatch(id: String): Completable = databaseRepository.deleteMatch(id)
}