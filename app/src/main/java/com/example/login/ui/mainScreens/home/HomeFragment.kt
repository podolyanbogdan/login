package com.example.login.ui.mainScreens.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.repository.ForecastRepository
import com.example.login.databinding.FragmentHomeBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
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
        viewModel.weekForecastTrigger.observe(this) {
            if (it) {
                navigate(HomeFragmentDirections.actionHomeFragmentToWeekForecastFragment())
                val bap = requireActivity().findViewById<BottomAppBar>(R.id.bottomAppBar)
                bap.visibility = View.GONE
            }
        }
        viewModel.message.observe(this) {
            snackBar(it)
        }
    }

}