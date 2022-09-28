package com.itrexgroup.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.itrexgroup.data.database.dao.MatchDao
import com.itrexgroup.data.database.models.MatchDb

@Database(
    entities = [
        MatchDb::class
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao
}