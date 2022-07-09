package com.example.login.ui.gamesScreens.bookScene

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.activity.addCallback
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.login.constants.ME
import com.example.login.R
import com.example.login.constants.SYLVIE
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.databinding.FragmentSceneBookBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
    

class SceneBookFragment : BaseFragment<FragmentSceneBookBinding>(R.layout.fragment_scene_book) {
    override val viewModel: SceneBookViewModel by viewModel()

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
        viewModel.displayCharacter.observe(this) {
            if (it == ME) binding.tvCharacter.setTextColor(Color.BLUE)
            if (it == SYLVIE) binding.tvCharacter.setTextColor(Color.GREEN)
        }
        viewModel.changeSilviaPic.observe(this) {
            if (it) { binding.imgSilvia.setImageResource(R.drawable.sylvie_green_smile) }
        }
        viewModel.jumpMarry.observe(this) {
            nextScene()
        }
    }

    private fun nextScene() = findNavController().safeNavigate(SceneBookFragmentDirections.fromBookToMarry())

    private fun NavController.safeNavigate(direction: NavDirections) {
        val animationOptions = NavOptions.Builder().setEnterAnim(R.anim.fade_in)
            .setExitAnim(R.anim.fade_out)
            .setPopEnterAnim(R.anim.fade_in)
            .setPopExitAnim(R.anim.fade_out).build()
        currentDestination?.getAction(direction.actionId)?.run {
            navigate(direction, animationOptions,)
        }
    }
}