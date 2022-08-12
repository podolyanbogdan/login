package com.example.login.ui.alert

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.login.R
import com.example.login.arch.BaseDialog
import com.example.login.data.constants.Constants
import com.example.login.data.enums.SettingsChoice
import com.example.login.databinding.FragmentSettingsDialogBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class SettingsDialogFragment : BaseDialog<FragmentSettingsDialogBinding>(R.layout.fragment_settings_dialog) {
    override val viewModel: SettingsDialogViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.permissionChoice.observe(this){ choice ->
            when(choice){
                SettingsChoice.CANCEL -> dismiss()
                SettingsChoice.GO_TO -> openSettings()
            }
        }
    }

    private fun openSettings(){
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts(Constants.PACKAGE_TAG, requireActivity().packageName, null)
        intent.data = uri
        startActivity(intent)
    }
}