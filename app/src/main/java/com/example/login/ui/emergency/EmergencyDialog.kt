package com.example.login.ui.emergency

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.login.databinding.EmergencyDialogFragmentBinding

class EmergencyDialog(
    private val onSaveClickResult: (Boolean) -> Unit,
) : DialogFragment() {
    private lateinit var binding: EmergencyDialogFragmentBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = EmergencyDialogFragmentBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.btnYes.setOnClickListener {
            onSaveClickResult.invoke(true)
            dismiss()
        }
        binding.btnNo.setOnClickListener {
            dismiss()
        }

        val dialog = builder.create()
        dialog.window!!.setGravity(Gravity.CENTER)
        return dialog
    }

}