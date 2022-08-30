package com.example.login.ui.introducingFragments.second

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.databinding.FragmentSecondIntroduceBinding
import com.example.login.ui.introducingFragments.thirth.ThirdIntroduceViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondIntroduceFragment : BaseFragment<FragmentSecondIntroduceBinding>(R.layout.fragment_second_introduce) {

    override val viewModel: SecondIntroduceViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        return view
    }
}