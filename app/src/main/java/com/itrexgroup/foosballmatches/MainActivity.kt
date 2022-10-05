package com.itrexgroup.foosballmatches

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.itrexgroup.foosballmatches.base.BaseActivity
import com.itrexgroup.foosballmatches.databinding.MainActivityBinding

class MainActivity : BaseActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host)
    }
}