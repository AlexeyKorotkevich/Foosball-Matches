package com.itrexgroup.foosballmatches

import android.app.Activity
import android.app.Application
import com.itrexgroup.foosballmatches.di.AppComponent
import com.itrexgroup.foosballmatches.di.DaggerAppComponent

class FoosballApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .factory()
            .create(this)
    }
}

val Activity.appComponent get() = (this.application as FoosballApplication).appComponent