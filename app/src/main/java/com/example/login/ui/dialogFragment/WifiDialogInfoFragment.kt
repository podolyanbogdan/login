package com.example.login.ui.dialogFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.login.R
import com.example.login.databinding.FragmentWifiDialogInfoBinding


class WifiDialogInfoFragment : DialogFragment() {
    private val dialogViewModel: DialogViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentWifiDialogInfoBinding = DataBindingUtil
            .inflate(LayoutInflater.from(context), R.layout.fragment_wifi_dialog_info, null, false)
        binding.viewmodel = dialogViewModel
        dialogViewModel.btnDissmiss.observe(this){result ->
            if(result == true){
                dismiss()
            }
        }
        dialogViewModel.btnDissmiss.value = false
        dialogViewModel.wifiName.observe(this){ result ->
            binding.tvDialogWifiName.text = result
        }
        dialogViewModel.wifiDescription.observe(this){ result ->
            binding.wifiDescription.text = result
        }

        return binding.root
    }


}