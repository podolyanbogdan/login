package com.example.login.ui.gamesScreens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.addCallback
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.databinding.FragmentStartScreenBinding
import com.example.login.ui.gamesViewModel.SceneLectureHallViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SceneLectureHall1Fragment : BaseFragment<FragmentStartScreenBinding>(R.layout.fragment_start_screen) {
    override val viewModel: SceneLectureHallViewModel by viewModel()
    private lateinit var inAnimation: Animation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        inAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        binding.imgSettings.setOnClickListener{navigate(R.id.settingsFragment)}
        requireActivity().onBackPressedDispatcher.addCallback(this) {}
        return view
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.changeBackground.observe(this){ if(it) binding.imgBg.setImageResource(R.drawable.bg_uni) }
        viewModel.jumpSceneLater.observe(this){ if(it) sceneBadEnding() }
        viewModel.jumpRightAway.observe(this){ if(it) sceneRightAway() }
        viewModel.showSilviaa.observe(this){
            binding.imgSilvia.visibility = View.VISIBLE
            binding.imgSilvia.startAnimation(inAnimation)
        }
    }

    private fun sceneBadEnding() = findNavController().safeNavigate(SceneLectureHall1FragmentDirections.fromStartToBadEnding())
    private fun sceneRightAway() = findNavController().safeNavigate(SceneLectureHall1FragmentDirections.fromStartToRightAway())

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