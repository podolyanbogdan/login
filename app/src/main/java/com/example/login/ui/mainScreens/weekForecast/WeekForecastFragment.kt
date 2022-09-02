package com.example.login.ui.mainScreens.weekForecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.databinding.FragmentWeekForecastBinding
import com.example.login.ui.mainScreens.weekForecast.adapter.ForecastWeekAdapter
import com.google.android.material.bottomappbar.BottomAppBar
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
                showBar()
            }
        }
        viewModel.weatherData.observe(this){
            forecastAdapter.submitList(it)
        }
        viewModel.message.observe(this){ msg ->
            if (msg.isNotEmpty()){
                snackBar(getString(R.string.smth_wrong))
            }
        }
    }

    private fun showBar(){
        val bap = requireActivity().findViewById<BottomAppBar>(R.id.bottomAppBar)
        bap.visibility = View.VISIBLE
    }
}