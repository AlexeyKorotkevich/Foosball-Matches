package com.itrexgroup.foosballmatches.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment() {

    protected val compositeDisposable = CompositeDisposable()
    protected var activity: BaseActivity? = null
    protected abstract fun observeData()

    // observe data only once
    private val observeData: Unit by lazy {
        observeData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity = requireActivity() as BaseActivity
        observeData
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    protected fun setTitle(title: String) {
        activity?.setPageTitle(title)
    }
}