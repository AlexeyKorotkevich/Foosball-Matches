package com.itrexgroup.foosballmatches.base

import androidx.fragment.app.DialogFragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseDialogFragment : DialogFragment() {
    protected val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}