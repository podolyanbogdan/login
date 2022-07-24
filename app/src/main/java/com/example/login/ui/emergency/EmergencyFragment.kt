package com.example.login.ui.emergency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.databinding.FragmentEmergencyBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EmergencyFragment : BaseFragment<FragmentEmergencyBinding>(R.layout.fragment_emergency) {

    override val viewModel: EmergencyViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        return view
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.trigger.observe(this) {
            if (it) showToast("Notes deleted")
        }
    }

}