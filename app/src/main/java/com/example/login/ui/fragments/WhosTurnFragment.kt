package com.example.login.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.login.R
import com.example.login.databinding.FragmentWhosTurnBinding
import com.example.login.viewmodels.GameViewModel
import com.example.login.viewmodels.SplashViewModel
import com.example.login.viewmodels.TurnsViewModel


class WhosTurnFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil
            .inflate<FragmentWhosTurnBinding>(
                inflater,
                R.layout.fragment_whos_turn,
                container,
                false
            )
        val myViewModel = ViewModelProvider(this)[TurnsViewModel::class.java]
        binding.viewmodel = myViewModel
        binding.lifecycleOwner = this
        myViewModel.isCompFirst.observe(viewLifecycleOwner, Observer { result ->
            if(result == true){
                val action = WhosTurnFragmentDirections.fromWhoTurnToGame(true)
                view?.let { Navigation.findNavController(it).navigate(action) }
            } else {
                view?.let { Navigation.findNavController(it).navigate(R.id.fromWhoTurnToGame) }
            }
        })
        moveBack()
        return binding.root
    }

    fun moveBack(){
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    view?.let { Navigation.findNavController(it).navigate(R.id.backFromWhoTurnToGameMode) }
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            callback
        )

    }
}