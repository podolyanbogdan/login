package com.example.login.ui.fragments.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.login.R
import com.example.login.databinding.FragmentCustomSplashhBinding
import com.example.login.viewmodels.SplashViewModel


class CustomSlashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil
            .inflate<FragmentCustomSplashhBinding>(
                inflater,
                R.layout.fragment_custom_splashh,
                container,
                false
            )
        val myViewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        binding.viewmodel = myViewModel
        binding.lifecycleOwner = this
        myViewModel.trigger.observe(viewLifecycleOwner){ result ->
            if(result == true) {
                view?.let { Navigation.findNavController(it).navigate(R.id.fromSplashToGameMode) }
            }
        }
        return binding.root
    }

}