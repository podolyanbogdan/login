package com.example.login.ui.gamesScreens.rigthAwayScene

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
import androidx.navigation.fragment.findNavController
import com.example.login.constants.ME
import com.example.login.R
import com.example.login.constants.SYLVIE
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.databinding.FragmentSceneMeadowBinding
import org.koin.androidx.viewmodel.ext.android.viewModel



class SceneRightAwayFragment : BaseFragment<FragmentSceneMeadowBinding>(R.layout.fragment_scene_meadow) {
    override val viewModel: SceneRightAwayModel by viewModel()
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
        viewModel.showSilviaa.observe(this){
            if(it){
                binding.imgSilvia.visibility = View.VISIBLE
                binding.imgSilvia.startAnimation(inAnimation)
            } else {
                binding.imgSilvia.visibility = View.INVISIBLE
            }
        }
        viewModel.changeBackground.observe(this){
            if(it) binding.imgBg.setImageResource(R.drawable.bg_meadow)
        }
        viewModel.changeSilviaPic.observe(this){
            if(it) binding.imgSilvia.setImageResource(R.drawable.sylvie_green_surprised)
        }
        viewModel.changeSilviaPic2.observe(this){
            if(it) binding.imgSilvia.setImageResource(R.drawable.sylvie_green_smile)
        }
        viewModel.jumpGame.observe(this){ if(it) sceneGame() }
        viewModel.jumpBook.observe(this){ if(it) sceneBook() }
        viewModel.displayCharacter.observe(this){
            if(it == ME) binding.tvCharacter.setTextColor(Color.BLUE)
            if(it == SYLVIE) binding.tvCharacter.setTextColor(Color.GREEN)
        }

    }

    private fun sceneGame() = findNavController().safeNavigate(SceneRightAwayFragmentDirections.fromRightAwayToGame())
    private fun sceneBook() = findNavController().safeNavigate(SceneRightAwayFragmentDirections.fromRightAwayToBook())
    private fun NavController.safeNavigate(direction: NavDirections) {
        currentDestination?.getAction(direction.actionId)?.run { navigate(direction) }
    }
}