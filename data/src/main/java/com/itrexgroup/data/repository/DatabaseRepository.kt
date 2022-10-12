package com.itrexgroup.data.repository

import com.itrexgroup.data.database.AppDatabase
import com.itrexgroup.data.ext.transformToMatchDb
import com.itrexgroup.data.ext.transformToMatchDto
import com.itrexgroup.domain.dto.MatchDto
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val database: AppDatabase
) {

    fun getMatchList(): Observable<List<MatchDto>> {
        return database.matchDao().getAllMatches().map { it.transformToMatchDto() }
    }

    fun updateMatch(matchDto: MatchDto) {
        database.matchDao().addMatch(matchDto.transformToMatchDb())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun deleteMatch(id: String) {
        database.matchDao().removeMatch(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}