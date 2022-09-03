package com.example.login.ui.mainScreens.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.databinding.FragmentHomeBinding
import com.google.android.material.bottomappbar.BottomAppBar
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override val viewModel: HomeViewModel by viewModel()

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
        viewModel.weekForecastTrigger.observe(this){
            if(it) navigate(HomeFragmentDirections.actionHomeFragmentToWeekForecastFragment())
            hideNavBar()
        }
        viewModel.message.observe(this){ msg ->
            if (msg.isNotEmpty()){
                snackBar(getString(R.string.smth_wrong))
            }
        }
    }

    private fun hideNavBar(){
        val bap = requireActivity().findViewById<BottomAppBar>(R.id.bottomAppBar)
        bap.visibility = View.GONE
    }

}