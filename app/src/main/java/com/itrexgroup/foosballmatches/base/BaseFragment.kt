package com.itrexgroup.foosballmatches.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected abstract fun observeData()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    protected fun setTitle(title: String) {
        val activity = requireActivity() as BaseActivity
        activity.setPageTitle(title)
    }
}