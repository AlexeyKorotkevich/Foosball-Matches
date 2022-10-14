package com.itrexgroup.foosballmatches.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    fun setPageTitle(title: String) {
        supportActionBar?.title = title
    }
}