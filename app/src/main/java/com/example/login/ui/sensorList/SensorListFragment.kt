package com.example.login.ui.sensorList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.House
import com.example.login.databinding.FragmentSensorListBinding
import com.example.login.repository.SensorRepository
import org.koin.androidx.viewmodel.ext.android.viewModel

class SensorListFragment : BaseFragment<FragmentSensorListBinding>(R.layout.fragment_sensor_list) {
    override val viewModel: SensorListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        initRecycler()
        syncByRefresh()
        return view
    }


    override fun setObservers() {
        super.setObservers()
        viewModel.addSensorTrigger.observe(this) {
            if (it) navigate(R.id.addSensorFragment)
        }
        viewModel.sensors.observe(this) {
            if (!it.isNullOrEmpty()) {
                viewModel.showLoadingGif.value = false
            }
        }
    }

    private fun initRecycler() {
        viewModel.sensors.observe(viewLifecycleOwner) {
            binding.recySensors.also {
                it.layoutManager = GridLayoutManager(requireContext(), 2)
                it.adapter = SensorAdapter(
                    viewModel.sensors.value as MutableList<House>,
                    SensorRepository()
                )
            }
        }
    }

    private fun syncByRefresh() {
        with(binding) {
            refreshLayout.setOnRefreshListener {
                viewModel.fetchSensorList()
                refreshLayout.isRefreshing = false
            }
        }
    }
}
