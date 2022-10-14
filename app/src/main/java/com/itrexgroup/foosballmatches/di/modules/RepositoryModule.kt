package com.itrexgroup.foosballmatches.di.modules

import com.itrexgroup.data.database.AppDatabase
import com.itrexgroup.data.repository.DatabaseRepositoryImpl
import com.itrexgroup.domain.repository.DatabaseRepository
import dagger.Module
import dagger.Provides

@Module
object RepositoryModule {

    @Provides
    fun provideDatabaseRepository(database: AppDatabase): DatabaseRepository =
        DatabaseRepositoryImpl(database)
}