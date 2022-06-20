package com.example.login.ui.splash

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
import com.example.login.ui.fragments.CellState


class CustomSlashFragment : Fragment() {
    private lateinit var binding: FragmentCustomSplashhBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomSplashhBinding.inflate(layoutInflater, container, false)
        binding.btnGoPlay.setOnClickListener {_->
            view?.let { Navigation.findNavController(it).navigate(R.id.fromSplashToGame) }
        }
        return binding.root
    }

}