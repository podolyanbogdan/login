package com.example.login.ui.mainScreens.cities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.databinding.FragmentCitiesBinding
import com.example.login.ui.mainScreens.cities.adapter.ForecastCitiesAdapter
import com.example.login.ui.mainScreens.cities.adapter.SwipeToDeleteCallback
import com.google.android.material.bottomappbar.BottomAppBar
import org.koin.androidx.viewmodel.ext.android.viewModel

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
        citiesAdapter = ForecastCitiesAdapter(viewModel)
        binding.citiesRec.apply {
            adapter = citiesAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            delete(this.adapter as ForecastCitiesAdapter)
        }
    }


    override fun setObservers() {
        super.setObservers()
        viewModel.cities.observe(this) {
            citiesAdapter.submitList(it)
        }
        viewModel.addCityTrigger.observe(this) {
            hideNavBar()
            if (it) navigate(CitiesFragmentDirections.actionCitiesFragmentToAddLocationFragment())
        }
        viewModel.showCityInfoTrigger.observe(this) {
            if (it) navigate(CitiesFragmentDirections.actionCitiesFragmentToHomeFragment())
        }
    }

    private fun hideNavBar() {
        val bap = requireActivity().findViewById<BottomAppBar>(R.id.bottomAppBar)
        bap.visibility = View.GONE
    }

    private fun delete(adapter: ForecastCitiesAdapter) {
        if ((viewModel.citiesRepoList.value?.size ?: 0) != 1) {
            val swipeGesture = object : SwipeToDeleteCallback(requireContext()) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    when (direction) {
                        ItemTouchHelper.LEFT -> removeItem(
                            viewHolder.bindingAdapterPosition,
                            viewHolder as ForecastCitiesAdapter.ForecastCitiesHolder
                        )
                    }
                }
            }
            val touchHelper = ItemTouchHelper(swipeGesture)
            touchHelper.attachToRecyclerView(binding.citiesRec)
        }
    }

    fun removeItem(
        position: Int,
        forecastCitiesHolder: ForecastCitiesAdapter.ForecastCitiesHolder
    ) {
        val currentList = viewModel.citiesRepoList.value
        currentList?.removeAt(position)
        viewModel.fetchData()
    }


}