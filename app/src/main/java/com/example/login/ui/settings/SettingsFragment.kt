package com.example.login.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.databinding.FragmentSettingsBinding
import com.example.login.music.Music
import org.koin.androidx.viewmodel.ext.android.viewModel


class SettingsFragment : BaseFragment<FragmentSettingsBinding>(R.layout.fragment_settings) {
    override val viewModel: SettingsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        return view
    }


    override fun setObservers() {
        super.setObservers()
        viewModel.isPlayAgain.observe(this) { if (it) playAgain() }
        viewModel.isContinue.observe(this) { if (it) activity?.onBackPressed(); }
        viewModel.isExit.observe(this) { if (it) activity?.finish() }
        viewModel.turnMusic.observe(this) { result -> if (result) context?.let { Music.startMusic(it) } }
        viewModel.offMusic.observe(this){ result -> if(result) Music.stopMusic() }
    }

    private fun playAgain() = findNavController().safeNavigate(SettingsFragmentDirections.fromSettingsToStartScreen())
    private fun NavController.safeNavigate(direction: NavDirections) {
        val animationOptions = NavOptions.Builder().setEnterAnim(R.anim.fade_in)
            .setExitAnim(R.anim.fade_out)
            .setPopEnterAnim(R.anim.fade_in)
            .setPopExitAnim(R.anim.fade_out).build()
        currentDestination?.getAction(direction.actionId)?.run {
            navigate(direction, animationOptions)
        }
    }
}