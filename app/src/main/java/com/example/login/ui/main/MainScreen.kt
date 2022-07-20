package com.example.login.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.login.R
import com.example.login.arch.BaseActivity
import com.example.login.constants.Constants
import com.example.login.databinding.ActivityMainBinding
import com.example.login.repository.PreferenceStorage
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreen : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myNavHostFragment =
            supportFragmentManager.findFragmentById(R.id.homeHostNavFragment) as NavHostFragment
        val inflater = myNavHostFragment.navController.navInflater
        val levelCharMain = inflater.inflate(R.navigation.level_char_main_graph)
        val charMain = inflater.inflate(R.navigation.char_main)
        val default = inflater.inflate(R.navigation.my_nav_graph)
        val onlyMain = inflater.inflate(R.navigation.without_all_graph)

        if (
            PreferenceStorage(applicationContext).checkAgeScreen(Constants.AGE_SCREEN) &&
            PreferenceStorage(applicationContext).checkLevelScreen(Constants.LEVEL_SCREEN) &&
            PreferenceStorage(applicationContext).checkCharacterScreen(Constants.CHARACTER_SCREEN)
        ) {
            myNavHostFragment.navController.graph = onlyMain
        }

        if (
            !PreferenceStorage(applicationContext).checkAgeScreen(Constants.AGE_SCREEN) &&
            !PreferenceStorage(applicationContext).checkLevelScreen(Constants.LEVEL_SCREEN) &&
            !PreferenceStorage(applicationContext).checkCharacterScreen(Constants.CHARACTER_SCREEN)
        ) {
            myNavHostFragment.navController.graph = default
        }

        if (
            PreferenceStorage(applicationContext).checkAgeScreen(Constants.AGE_SCREEN) &&
            !PreferenceStorage(applicationContext).checkLevelScreen(Constants.LEVEL_SCREEN)
        ) {
            myNavHostFragment.navController.graph = levelCharMain
        }

        if (
            PreferenceStorage(applicationContext).checkAgeScreen(Constants.AGE_SCREEN) &&
            PreferenceStorage(applicationContext).checkLevelScreen(Constants.LEVEL_SCREEN) &&
            !PreferenceStorage(applicationContext).checkCharacterScreen(Constants.CHARACTER_SCREEN)
        ) {
            myNavHostFragment.navController.graph = charMain
        }
    }

    override val navController: NavController
        get() = TODO()

}