package com.example.login.ui.splash

import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.constants.Constants
import com.example.login.data.ScreensState
import com.example.login.databinding.FragmentSplashBinding
import com.example.login.repository.PreferenceStorage
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.system.exitProcess

class SplashFragment : BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

    override val viewModel: SplashViewModel by viewModel()

    override fun setObservers() {
        viewModel.currentNavigation.observe(this){ states ->
           when(states){
               ScreensState.MAP -> navigate(R.id.parentActivity)
               ScreensState.AGE -> navigate(R.id.ageFragment)
               ScreensState.LEVEL -> navigate(R.id.levelFragment)
               ScreensState.CHARACTER -> navigate(R.id.characterFragment)
               else -> exitProcess(1)
           }
        }
    }

}