package com.example.login.ui.mainScreens.weekForecast

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
import com.example.login.databinding.FragmentWeekForecastBinding
import com.example.login.ui.mainScreens.cities.adapter.ForecastCitiesAdapter
import com.example.login.ui.mainScreens.weekForecast.adapter.ForecastWeekAdapter
import com.google.android.material.bottomappbar.BottomAppBar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeekForecastFragment :
    BaseFragment<FragmentWeekForecastBinding>(R.layout.fragment_week_forecast) {
    override val viewModel: WeekForecastViewModel by viewModel()
    private lateinit var forecastAdapter: ForecastWeekAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        initRecycler()
        lifecycleScope.launch {
            Log.d("TEST", "${ForecastRepository().getDailyForecast()}")
            Log.d("TEST", "${ForecastRepository().getDailyForecast().body()}")
        }
        return view
    }

    private fun initRecycler() {
        forecastAdapter = ForecastWeekAdapter()
        binding.weekRec.apply {
            adapter = forecastAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }


    override fun setObservers() {
        super.setObservers()
        viewModel.onBackTrigger.observe(this) {
            if (it) {
                navigate(WeekForecastFragmentDirections.actionWeekForecastFragmentToHomeFragment())
                val bap = requireActivity().findViewById<BottomAppBar>(R.id.bottomAppBar)
                bap.visibility = View.VISIBLE
            }
        }
        viewModel.weatherData.observe(this){
            forecastAdapter.submitList(it)
        }
        viewModel.message.observe(this){
            snackBar("No internet connection")
        }
    }
}