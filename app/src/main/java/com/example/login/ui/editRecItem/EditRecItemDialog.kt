package com.example.login.ui.editRecItem

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
import com.example.login.databinding.FragmentEditRecItemBinding
import com.example.login.ui.screens.addTask.AddTaskViewModel

class EditRecItemDialog(
    private val titleRes: (String) -> Unit,
    private val timeBoth: (String) -> Unit,
) : DialogFragment() {
    private lateinit var binding: FragmentEditRecItemBinding
    private val viewModel: AddTaskViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentEditRecItemBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        var first = 0
        var second = 0
        var third = 0
        var fourth = 0

        binding.firstPicker.setOnValueChangedListener { numberPicker, i, i2 ->
            first = numberPicker.value
        }

        binding.secondPicker.setOnValueChangedListener { numberPicker, i, i2 ->
            second = numberPicker.value
        }

        binding.thirdPicker.setOnValueChangedListener { numberPicker, i, i2 ->
            third = numberPicker.value
        }

        binding.fourthPicker.setOnValueChangedListener { numberPicker, i, i2 ->
            fourth = numberPicker.value
        }

        binding.btnSave.setOnClickListener {
           if(binding.edTitleEdit.text.isNullOrEmpty()){
               Toast.makeText(context, "You need to edit Title", Toast.LENGTH_SHORT).show()
           } else {
               var result = "$first:$second - $third:$fourth"
               val title = binding.edTitleEdit.text.toString()
               titleRes.invoke(title)
               timeBoth.invoke(result)
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