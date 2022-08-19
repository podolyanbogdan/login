package com.example.login.ui.screens.advanced

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.addCallback
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.enumss.FieldsStatus
import com.example.login.data.enumss.From
import com.example.login.databinding.FragmentAdvancedSearchBinding
import com.example.login.internetCheckign.ConnectionLiveData
import com.example.login.ui.screens.tab.TabFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel


class AdvancedFragment :
    BaseFragment<FragmentAdvancedSearchBinding>(R.layout.fragment_advanced_search) {

    override val viewModel: AdvancedViewModel by viewModel()
    private lateinit var cld: ConnectionLiveData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        requireActivity().onBackPressedDispatcher.addCallback(this) {}
        initDropDownType()
        checkNetworkConnection()
        return view
    }

    private fun checkNetworkConnection() {
        cld = ConnectionLiveData(requireActivity().application)
        cld.observe(viewLifecycleOwner) { isConnected ->
            if (isConnected) {
                viewModel.wifiState.value = true
            }
            if (isConnected == false) {
                viewModel.wifiState.value = false
            }
        }
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.onSearchTrigger.observe(this) { status ->
            when (status) {
                FieldsStatus.FILLED -> navigate(TabFragmentDirections.fromTabFragmentToBirdList(
                    queryString = viewModel.advancedQuery.value?: "",
                    querySearchType = From.ADVANCED
                ))
                FieldsStatus.EMPTY -> showToast(getString(R.string.you_need_to_pass_at_least))
            }
        }
    }

    private fun initDropDownType() {
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.countries_array)
        )
        binding.autoCompleteType.setAdapter(arrayAdapter)
    }
}