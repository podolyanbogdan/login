package com.example.login.ui.main

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }
    override fun setObservers() {}



}