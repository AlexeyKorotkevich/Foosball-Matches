package com.itrexgroup.foosballmatches.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseDialogFragment : DialogFragment() {
    protected val compositeDisposable = CompositeDisposable()
    protected abstract fun observeData()

    // observe data only once
    private val observeData: Unit by lazy {
        observeData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}