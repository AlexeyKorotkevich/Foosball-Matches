package com.itrexgroup.foosballmatches.repository

import com.itrexgroup.data.database.AppDatabase
import com.itrexgroup.data.ext.transformToMatchDto
import com.itrexgroup.data.ext.transformToPlayerDto
import com.itrexgroup.domain.dto.MatchDto
import com.itrexgroup.domain.dto.PlayerDto
import io.reactivex.Observable
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val database: AppDatabase
) {
    fun getMatchList(): Observable<List<MatchDto>> {
        return database.matchDao().getAllMatches().map { it.transformToMatchDto() }
    }

    fun getPlayerList(): Observable<List<PlayerDto>> {
        return database.playerDao().getAllPlayers().map { it.transformToPlayerDto() }
    }
}