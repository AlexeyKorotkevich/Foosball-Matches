package com.itrexgroup.foosballmatches.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected var activity: BaseActivity? = null
    protected abstract fun observeData()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity = requireActivity() as BaseActivity
        observeData()
    }

    protected fun setTitle(title: String) {
        activity?.setPageTitle(title)
    }
}