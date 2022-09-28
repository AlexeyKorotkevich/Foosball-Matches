package com.itrexgroup.foosballmatches.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel: ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}