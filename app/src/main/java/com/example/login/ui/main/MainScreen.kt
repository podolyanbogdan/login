package com.example.login.ui.main

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.login.R
import com.example.login.arch.BaseActivity
import com.example.login.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreen : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModel()

    override val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.homeHostNavFragment) as NavHostFragment
        navHostFragment.navController
    }
    override fun setObservers() {}

}