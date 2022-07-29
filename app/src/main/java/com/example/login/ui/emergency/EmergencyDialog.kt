package com.example.login.ui.emergency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.login.databinding.EmergencyDialogFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class EmergencyDialog : DialogFragment() {
    private val viewModel: DialogViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = EmergencyDialogFragmentBinding.inflate(inflater, container, false)
            .apply {
                this.lifecycleOwner = this@EmergencyDialog
                this.viewmodel = this@EmergencyDialog.viewModel
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.yesTrigger.observe(viewLifecycleOwner){
            if(it) dismiss()
        }
        viewModel.noTrigger.observe(viewLifecycleOwner){
            if(it) dismiss()
        }
        super.onViewCreated(view, savedInstanceState)

    }
}