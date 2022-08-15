package com.example.login.ui.screens.advanced

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.databinding.FragmentAdvancedSearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdvancedFragment : BaseFragment<FragmentAdvancedSearchBinding>(R.layout.fragment_advanced_search) {

    override val viewModel: AdvancedViewModel by viewModel()

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
        viewModel.onSearchTrigger.observe(this){
           navigate(R.id.birdsListFragment)
        }
    }

}