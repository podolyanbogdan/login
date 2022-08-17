package com.example.login.ui.screens.defaultt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.enumss.FieldsStatus
import com.example.login.data.enumss.From
import com.example.login.databinding.FragmentAdvancedSearchBinding
import com.example.login.databinding.FragmentDefaultSearchBinding
import com.example.login.internetCheckign.ConnectionLiveData
import com.example.login.ui.screens.advanced.AdvancedViewModel
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
        checkNetworkConnection()
        return view
    }

    private fun checkNetworkConnection() {
        cld = ConnectionLiveData(requireActivity().application)
        cld.observe(viewLifecycleOwner) { isConnected ->
            if (isConnected) {
                viewModel.wifiState.value = true
            }
            if(isConnected == false){
                viewModel.wifiState.value = false
            }
        }
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.goAdvancedTrigger.observe(this) {
            navigate(R.id.advancedFragment)
        }
        viewModel.onSearchTrigger.observe(this) { status ->
           when(status){
               FieldsStatus.FILLED -> navigate(R.id.birdsListFragment)
               FieldsStatus.EMPTY -> showToast(getString(R.string.you_need_to_pass_data))
           }
        }
    }

}