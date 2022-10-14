package com.itrexgroup.foosballmatches.di.modules

import com.itrexgroup.domain.repository.DatabaseRepository
import com.itrexgroup.domain.usecase.MatchListUseCase
import com.itrexgroup.domain.usecase.PlayerListUseCase
import dagger.Module
import dagger.Provides

@Module
object UseCaseModule {

    @Provides
    fun provideMatchListUseCase(repository: DatabaseRepository): MatchListUseCase =
        MatchListUseCase(repository)

    @Provides
    fun providePlayerListUseCase(repository: DatabaseRepository): PlayerListUseCase =
        PlayerListUseCase(repository)
}