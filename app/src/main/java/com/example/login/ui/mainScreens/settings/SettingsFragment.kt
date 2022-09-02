package com.example.login.ui.mainScreens.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.databinding.FragmentSettingsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(R.layout.fragment_settings) {
    override val viewModel: SettingsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        initDropDownType()
        return view
    }

    private fun initDropDownType() {
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.languages_array)
        )
        binding.autoCompleteType.setAdapter(arrayAdapter)
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.darkModeState.observe(this) { state ->
            if (state) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        viewModel.showAlertsState.observe(this) { state ->
            viewModel.alertsVisible.value = state
        }
        viewModel.languageState.observe(this){ lang ->
            Toast.makeText(context, lang, Toast.LENGTH_SHORT).show()
        }
    }

}