package com.example.login.ui.screens.defaultt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.enumss.FieldsStatus
import com.example.login.data.enumss.From
import com.example.login.databinding.FragmentDefaultSearchBinding
import com.example.login.internetCheckign.ConnectionLiveData
import com.example.login.ui.screens.tab.TabFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel


class DefaultFragment :
    BaseFragment<FragmentDefaultSearchBinding>(R.layout.fragment_default_search) {
    private lateinit var cld : ConnectionLiveData
    override val viewModel: DefaultViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        requireActivity().onBackPressedDispatcher.addCallback(this) {}
        checkNetworkConnection()
        return view
    }

    private fun checkNetworkConnection() {
        cld = ConnectionLiveData(requireActivity().application)
        cld.observe(viewLifecycleOwner) { isConnected ->
            if (isConnected) {
                viewModel.wifiState.value = false
            }
            if(isConnected == false){
                viewModel.wifiState.value = true
            }
        }
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.onSearchTrigger.observe(this) { status ->
           when(status){
               FieldsStatus.FILLED -> navigate(TabFragmentDirections.fromTabFragmentToBirdList(
                   queryString = viewModel.defaultRequestValue.value ?: "",
                   querySearchType = From.DEFAULT
               ))
               FieldsStatus.EMPTY -> showToast(getString(R.string.you_need_to_pass_data))
           }
        }
    }

}