package com.example.login.ui.splash

import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.databinding.SplashFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<SplashFragmentBinding>(R.layout.splash_fragment) {

    override val viewModel: SplashViewModel by viewModel()
    override fun setObservers() {
        viewModel.initEvent.observe(this) {
            if (it) showWifiFragment()
        }
    }
    private fun showWifiFragment() {
        navigate(R.id.translatorFragment)
    }
}