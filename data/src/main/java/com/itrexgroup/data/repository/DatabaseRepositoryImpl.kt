package com.itrexgroup.data.repository

import com.itrexgroup.data.database.AppDatabase
import com.itrexgroup.data.ext.transformToMatchDb
import com.itrexgroup.data.ext.transformToMatchDto
import com.itrexgroup.domain.dto.MatchDto
import com.itrexgroup.domain.repository.DatabaseRepository
import io.reactivex.Completable
import io.reactivex.Observable

class DatabaseRepositoryImpl(
    private val database: AppDatabase
) : DatabaseRepository {

    override fun getMatchList(): Observable<List<MatchDto>> {
        return database.matchDao().getAllMatches().map { it.transformToMatchDto() }
    }

    override fun updateMatch(matchDto: MatchDto): Completable =
        database.matchDao().addMatch(matchDto.transformToMatchDb())

    override fun deleteMatch(id: String): Completable = database.matchDao().removeMatch(id)
}