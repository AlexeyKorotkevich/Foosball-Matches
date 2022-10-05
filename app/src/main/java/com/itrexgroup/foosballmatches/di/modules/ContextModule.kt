package com.itrexgroup.foosballmatches.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ContextModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideAppContext(application: Application): Context = application.applicationContext
}