package com.example.login.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.databinding.FragmentSettingsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class SettingsFragment : BaseFragment<FragmentSettingsBinding>(R.layout.fragment_settings) {

    override val viewModel: SettingsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        return view
    }

}