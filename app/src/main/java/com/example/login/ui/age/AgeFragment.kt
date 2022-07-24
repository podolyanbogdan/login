package com.example.login.ui.age

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.databinding.FragmentAgeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class AgeFragment : BaseFragment<FragmentAgeBinding>(R.layout.fragment_age) {
    override val viewModel: AgeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        return view
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.goNextScreen.observe(this) {
            if (it) navigate(R.id.levelFragment)
            else showToast(getString(R.string.you_need_confirm_your_age))
        }
    }
}