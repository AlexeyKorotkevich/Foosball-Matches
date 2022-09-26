package com.itrexgroup.data.database.dao

import androidx.room.*
import com.itrexgroup.data.database.models.PlayerDb
import io.reactivex.Observable

@Dao
interface PlayerDao {
    @Query("SELECT * FROM PlayerDb")
    fun getAllPlayers(): Observable<List<PlayerDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPlayer(player: PlayerDb)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatePlayer(player: PlayerDb)

    @Query("DELETE FROM PlayerDb WHERE id = :playerId")
    fun removePlayer(playerId: String)
}