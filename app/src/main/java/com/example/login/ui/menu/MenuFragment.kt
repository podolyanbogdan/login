package com.example.login.ui.menu

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.databinding.FragmentMenuBinding
import com.example.login.music.Music
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException


class MenuFragment : BaseFragment<FragmentMenuBinding>(R.layout.fragment_menu) {
    override val viewModel: MenuViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        startMusic()
        return view
    }

    private fun startMusic(){ context?.let { Music.startMusic(it) } }

    override fun setObservers() {
        super.setObservers()
        viewModel.play.observe(this){
            if(it) startGame()
        }
        viewModel.exit.observe(this){
            if(it) exitGame()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Music.stopMusic()
    }

    private fun startGame(){
        val animationOptions = NavOptions.Builder().setEnterAnim(R.anim.fade_in)
            .setExitAnim(R.anim.fade_out)
            .setPopEnterAnim(R.anim.fade_in)
            .setPopExitAnim(R.anim.fade_out).build()
        findNavController().navigate(MenuFragmentDirections.fromMenuToStart(), animationOptions)
    }
    private fun exitGame(){
        activity?.finish()
    }
}