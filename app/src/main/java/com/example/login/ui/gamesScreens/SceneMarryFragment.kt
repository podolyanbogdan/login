package com.example.login.ui.gamesScreens

import android.graphics.Color
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
import androidx.navigation.fragment.navArgs
import com.example.login.ME
import com.example.login.R
import com.example.login.SYLVIE
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.databinding.FragmentSceneMarryBinding
import com.example.login.ui.gamesViewModel.SceneMarryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SceneMarryFragment : BaseFragment<FragmentSceneMarryBinding>(R.layout.fragment_scene_marry) {
    override val viewModel: SceneMarryViewModel by viewModel()
    private lateinit var inAnimation: Animation
    private lateinit var outAnimation: Animation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        inAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        outAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_out)
        binding.imgSettings.setOnClickListener{navigate(R.id.settingsFragment)}
        requireActivity().onBackPressedDispatcher.addCallback(this) {}
        return view
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.changeBackground.observe(this){
            if(it == 1) {
                binding.imgBg.setImageResource(R.drawable.bg_club)
            }
            if(it == 2) {
                binding.imgBg.setImageResource(R.drawable.black_screen)
                binding.imgBg.startAnimation(inAnimation)
            }
        }
        viewModel.displayCharacter.observe(this){
            if(it == ME) binding.tvCharacter.setTextColor(Color.BLUE)
            if(it == SYLVIE) binding.tvCharacter.setTextColor(Color.GREEN)
        }
        viewModel.showSilviaa.observe(this){
            if (it){
                binding.imgSilvia.visibility = View.VISIBLE
                binding.imgSilvia.startAnimation(inAnimation)
            } else { binding.imgSilvia.visibility = View.INVISIBLE }
        }
        viewModel.changeSilviaPic.observe(this){
            when(it){
                1 -> binding.imgSilvia.setImageResource(R.drawable.sylvie_blue_normal)
                2 -> binding.imgSilvia.setImageResource(R.drawable.sylvie_blue_giggle)
                3 -> binding.imgSilvia.setImageResource(R.drawable.sylvie_blue_surprised)
                4 -> binding.imgSilvia.setImageResource(R.drawable.sylvie_blue_smile)
                5 -> binding.imgSilvia.setImageResource(R.drawable.sylvie_blue_giggle)
                6 -> binding.imgSilvia.setImageResource(R.drawable.sylvie_blue_normal)
                7 -> binding.imgSilvia.setImageResource(R.drawable.sylvie_blue_giggle)
            }
        }
        viewModel.jumpMenu.observe(this){ nextScene() }
        viewModel.playAgain.observe(this) { if (it) nextScene() }
        viewModel.exit.observe(this) { if (it) exit() }
    }

    private fun nextScene() = findNavController().safeNavigate(SceneMarryFragmentDirections.fromHappyEndToStartGame())
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