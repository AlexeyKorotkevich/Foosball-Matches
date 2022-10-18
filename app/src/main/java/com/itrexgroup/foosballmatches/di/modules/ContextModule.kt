package com.itrexgroup.foosballmatches.di.modules

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ContextModule {

    @Binds
    @Singleton
    abstract fun bindAppContext(application: Application): Context
}