package com.example.login.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.login.R
import com.example.login.databinding.FragmentGameModeBinding
import com.example.login.databinding.FragmentWhosTurnBinding
import com.example.login.viewmodels.GameModeViewModel
import com.example.login.viewmodels.GameViewModel
import com.example.login.viewmodels.TurnsViewModel

class GameModeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil
            .inflate<FragmentGameModeBinding>(
                inflater,
                R.layout.fragment_game_mode,
                container,
                false
            )
        val myViewModel = ViewModelProvider(this)[GameModeViewModel::class.java]
        binding.viewmodel = myViewModel
        binding.lifecycleOwner = this

        myViewModel.playerComputerMode.observe(viewLifecycleOwner){ result ->
            if(result == true){
                view?.let { Navigation.findNavController(it).navigate(R.id.fromGameModeToWhoTurns) }
            }
        }

        myViewModel.playerPlayerMode.observe(viewLifecycleOwner){result ->
            if(result == true){
                val action = GameModeFragmentDirections.fromGameModeToMainGame(
                    isCompFirst = false,
                    playerPlayerMode = true
                )
                view?.let { Navigation.findNavController(it).navigate(action) }
            }
        }
        moveBack()
        return binding.root
    }

    fun moveBack(){
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    view?.let { Navigation.findNavController(it).navigate(R.id.backFromGameModeToSplash) }
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            callback
        )
    }

}