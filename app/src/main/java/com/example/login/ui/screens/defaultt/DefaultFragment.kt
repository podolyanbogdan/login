package com.example.login.ui.screens.defaultt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.enumss.From
import com.example.login.databinding.FragmentAdvancedSearchBinding
import com.example.login.databinding.FragmentDefaultSearchBinding
import com.example.login.ui.screens.advanced.AdvancedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DefaultFragment :
    BaseFragment<FragmentDefaultSearchBinding>(R.layout.fragment_default_search) {

    override val viewModel: DefaultViewModel by viewModel()

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
        viewModel.goAdvancedTrigger.observe(this) {
            navigate(R.id.advancedFragment)
        }
        viewModel.onSearchTrigger.observe(this) {
            navigate(R.id.birdsListFragment)
        }
    }

}