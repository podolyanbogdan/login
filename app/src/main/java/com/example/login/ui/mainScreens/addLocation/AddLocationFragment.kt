package com.example.login.ui.mainScreens.addLocation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.databinding.FragmentAddLocationBinding
import com.example.login.ui.mainScreens.home.HomeViewModel
import com.google.android.material.bottomappbar.BottomAppBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddLocationFragment :
    BaseFragment<FragmentAddLocationBinding>(R.layout.fragment_add_location) {
    override val viewModel: AddLocationViewModel by viewModel()

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
        viewModel.addCityTrigger.observe(this) {
            showNavBar()
            if (it) navigate(AddLocationFragmentDirections.actionAddLocationFragmentToCitiesFragment())
        }
    }

    private fun showNavBar() {
        val bap = requireActivity().findViewById<BottomAppBar>(R.id.bottomAppBar)
        bap.visibility = View.VISIBLE
    }
}