package com.example.login.ui.splash

import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.constants.Constants
import com.example.login.databinding.FragmentSplashBinding
import com.example.login.repository.PreferenceStorage
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

    override val viewModel: SplashViewModel by viewModel()

    override fun setObservers() {
        viewModel.initEvent.observe(this) {
            if (it) showAgeFragment()
        }
    }
    private fun showAgeFragment() {
        if (
            PreferenceStorage(requireContext()).checkAgeScreen(Constants.AGE_SCREEN) &&
            PreferenceStorage(requireContext()).checkLevelScreen(Constants.LEVEL_SCREEN) &&
            PreferenceStorage(requireContext()).checkCharacterScreen(Constants.CHARACTER_SCREEN)
        ) {
           navigate(R.id.parentActivity)
        }

        if (
            !PreferenceStorage(requireContext()).checkAgeScreen(Constants.AGE_SCREEN) &&
            !PreferenceStorage(requireContext()).checkLevelScreen(Constants.LEVEL_SCREEN) &&
            !PreferenceStorage(requireContext()).checkCharacterScreen(Constants.CHARACTER_SCREEN)
        ) {
            navigate(R.id.ageFragment)
        }

        if (
            PreferenceStorage(requireContext()).checkAgeScreen(Constants.AGE_SCREEN) &&
            !PreferenceStorage(requireContext()).checkLevelScreen(Constants.LEVEL_SCREEN) &&
            !PreferenceStorage(requireContext()).checkCharacterScreen(Constants.CHARACTER_SCREEN)
        ) {
            navigate(R.id.levelFragment)
        }

        if (
            PreferenceStorage(requireContext()).checkAgeScreen(Constants.AGE_SCREEN) &&
            PreferenceStorage(requireContext()).checkLevelScreen(Constants.LEVEL_SCREEN)&&
            !PreferenceStorage(requireContext()).checkCharacterScreen(Constants.CHARACTER_SCREEN)
        ) {
            navigate(R.id.characterFragment3)
        }
    }

}