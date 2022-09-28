package com.itrexgroup.data.database.dao

import androidx.room.*
import com.itrexgroup.data.database.models.MatchDb
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface MatchDao {
    @Query("SELECT * FROM MatchDb")
    fun getAllMatches(): Observable<List<MatchDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMatch(match: MatchDb): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMatch(match: MatchDb): Completable

    @Query("DELETE FROM MatchDb WHERE id = :matchId")
    fun removeMatch(matchId: String): Completable
}