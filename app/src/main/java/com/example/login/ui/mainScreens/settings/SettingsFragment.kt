package com.example.login.ui.mainScreens.settings

import android.content.Context
import android.content.res.Resources
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
import com.yariksoffice.lingver.Lingver
import org.koin.androidx.viewmodel.ext.android.viewModel


class SettingsFragment : BaseFragment<FragmentSettingsBinding>(R.layout.fragment_settings) {
    override val viewModel: SettingsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        binding.tvEnglish.setOnClickListener { changeLanguage("en") }
        binding.tvRus.setOnClickListener { changeLanguage("rus") }
        return view
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
    }

    private fun changeLanguage(lang: String){
        context?.let { Lingver.getInstance().setLocale(it, lang) }
        requireActivity().recreate()
    }

}