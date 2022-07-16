package com.example.login.ui.dialogFragment

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.login.R
import com.example.login.databinding.FragmentCreateTagBinding

class TagDialogFragment(
    private val onSaveClickListener: (String) -> Unit
) : DialogFragment() {
    private lateinit var binding: FragmentCreateTagBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentCreateTagBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.btnSave.setOnClickListener {
            if (binding.edDialogTag.text.isNullOrEmpty()) {
                Toast.makeText(context, getString(R.string.add_tag_name_pls), Toast.LENGTH_SHORT)
                    .show()
            } else {
                onSaveClickListener.invoke(binding.edDialogTag.text.toString())
                dismiss()
            }
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setGravity(Gravity.CENTER)
        return dialog
    }
}