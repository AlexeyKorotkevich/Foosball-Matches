package com.itrexgroup.foosballmatches.di

import android.app.Application
import com.itrexgroup.foosballmatches.di.modules.ContextModule
import com.itrexgroup.foosballmatches.di.modules.DatabaseModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}