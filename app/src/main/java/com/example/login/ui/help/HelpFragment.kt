package com.example.login.ui.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.databinding.FragmentHelpBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HelpFragment : BaseFragment<FragmentHelpBinding>(R.layout.fragment_help) {
    override val viewModel: HelpViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        return view
    }


}