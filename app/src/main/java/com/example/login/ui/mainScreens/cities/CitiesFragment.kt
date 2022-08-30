package com.example.login.ui.mainScreens.cities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.repository.ForecastRepository
import com.example.login.databinding.FragmentCitiesBinding
import com.example.login.ui.mainScreens.cities.adapter.ForecastCitiesAdapter
import com.example.login.ui.mainScreens.weekForecast.WeekForecastFragmentDirections
import com.example.login.ui.mainScreens.weekForecast.adapter.ForecastWeekAdapter
import com.google.android.material.bottomappbar.BottomAppBar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class CitiesFragment : BaseFragment<FragmentCitiesBinding>(R.layout.fragment_cities) {
    override val viewModel: CitiesViewModel by viewModel()
    private lateinit var citiesAdapter: ForecastCitiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        initRecycler()
        return view
    }

    private fun initRecycler() {
        citiesAdapter = ForecastCitiesAdapter()
        binding.citiesRec.apply {
            adapter = citiesAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }


    override fun setObservers() {
        super.setObservers()
        viewModel.forecastData.observe(this){
            citiesAdapter.submitList(it)
        }
    }

}