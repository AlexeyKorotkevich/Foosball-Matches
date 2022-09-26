package com.itrexgroup.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.itrexgroup.data.database.dao.MatchDao
import com.itrexgroup.data.database.dao.PlayerDao
import com.itrexgroup.data.database.models.MatchDb
import com.itrexgroup.data.database.models.PlayerDb

@Database(
    entities = [
        MatchDb::class,
        PlayerDb::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao

    abstract fun playerDao(): PlayerDao
}