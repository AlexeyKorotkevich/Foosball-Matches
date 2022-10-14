package com.itrexgroup.domain.repository

import com.itrexgroup.domain.dto.MatchDto
import io.reactivex.Observable

interface DatabaseRepository {

    fun getMatchList(): Observable<List<MatchDto>>

    fun updateMatch(matchDto: MatchDto)

    fun deleteMatch(id: String)
}