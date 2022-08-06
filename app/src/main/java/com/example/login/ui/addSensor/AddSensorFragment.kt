package com.example.login.ui.addSensor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.Subtypes
import com.example.login.data.Types
import com.example.login.databinding.FragmentAddSensorBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddSensorFragment : BaseFragment<FragmentAddSensorBinding>(R.layout.fragment_add_sensor) {
    override val viewModel: AddSensorViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        initDropDownType()
        initDropDownSubType()
        return view
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.createSensorTrigger.observe(this) {
            if (it) navigate(R.id.sensorFragment)
        }
    }

    private fun initDropDownType() {
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            Types.values()
        )
        binding.autoCompleteType.setAdapter(arrayAdapter)
    }

    private fun initDropDownSubType() {
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            Subtypes.values()
        )
        binding.autoCompleteSubtype.setAdapter(arrayAdapter)
    }

}