package com.itrexgroup.foosballmatches.di.modules

import android.content.Context
import androidx.room.Room
import com.itrexgroup.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "app-database")
            .fallbackToDestructiveMigration()
            .build()
    }
}