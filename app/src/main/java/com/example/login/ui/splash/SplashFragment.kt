package com.example.login.ui.splash

import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.enumss.IntroduceScreenState
import com.example.login.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

    override val viewModel: SplashViewModel by viewModel()
    override fun setObservers() {
        viewModel.screenState.observe(this) { state ->
            when(state){
                IntroduceScreenState.LOCATION_ACCEPT -> navigate(SplashFragmentDirections.fromSplashToAppBarActivity())
                IntroduceScreenState.WATCHED -> navigate(SplashFragmentDirections.actionSplashFragmentToPermissionAndLocaleFragment())
                IntroduceScreenState.UNWATCHED -> navigate(SplashFragmentDirections.fromSplashToViewPageFragment())
            }
        }
    }
}