package com.itrexgroup.domain.repository

import com.itrexgroup.domain.dto.MatchDto
import io.reactivex.Completable
import io.reactivex.Observable

interface DatabaseRepository {

    fun getMatchList(): Observable<List<MatchDto>>

    fun updateMatch(matchDto: MatchDto): Completable

    fun deleteMatch(id: String): Completable
}