package com.example.login.ui.gamesScreens.badEndingScene

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.databinding.FragmentSceneLaterBadEndingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class SceneLaterBadEndingFragment : BaseFragment<FragmentSceneLaterBadEndingBinding>(R.layout.fragment_scene_later_bad_ending) {
    override val viewModel: SceneLaterBadEndingViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        binding.imgSettings.setOnClickListener{navigate(R.id.settingsFragment)}
        requireActivity().onBackPressedDispatcher.addCallback(this) {}
        return view
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.returnInToMenu.observe(this) { if (it) nextScene() }
        viewModel.exit.observe(this) { if (it) exit() }
    }

    private fun nextScene() = findNavController().safeNavigate(SceneLaterBadEndingFragmentDirections.fromBadToStartGame())
    private fun NavController.safeNavigate(direction: NavDirections) {
        val animationOptions = NavOptions.Builder().setEnterAnim(R.anim.fade_in)
            .setExitAnim(R.anim.fade_out)
            .setPopEnterAnim(R.anim.fade_in)
            .setPopExitAnim(R.anim.fade_out).build()
        currentDestination?.getAction(direction.actionId)?.run {
            navigate(direction, animationOptions)
        }
    }

    private fun exit() {
        activity?.finish()
    }
}