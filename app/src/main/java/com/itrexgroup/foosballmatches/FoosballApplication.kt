package com.itrexgroup.foosballmatches

import android.app.Application
import androidx.fragment.app.Fragment
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

val Fragment.appComponent get() = (this.requireActivity().application as FoosballApplication).appComponent